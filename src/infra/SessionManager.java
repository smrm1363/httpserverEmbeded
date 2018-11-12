package infra;

import domain.Customer;
import domain.CustomerSession;

import java.time.LocalTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This is our session manager. This is a Singleton class
 */
public class SessionManager {
    private Map<String , CustomerSession> sessionMap;
    private final int sessionTimeOut = 10;
    private final int RemoveExpiredSessionInterval = 20;
    private static SessionManager sessionManager;

    private SessionManager() {
        /**
         * I use ConcurrentHashMap for being thread safe
         */
        this.sessionMap = new ConcurrentHashMap<>();
        runRemoveExpiredSessions();
    }

    /**
     *
     * @return a singleton instance of SessionManager
     */
    public static SessionManager getInstance()
    {
        if(sessionManager == null)
            sessionManager = new SessionManager() ;
        return sessionManager;
    }

    public Map<String, CustomerSession> getSessionMap() {
        return sessionMap;
    }

    /**
     *
     * @param customer
     * @return a unique session key
     */
    public String createSession(Customer customer)
    {
        UUID uuid = UUID.randomUUID();
        sessionMap.put(uuid.toString(),new CustomerSession(customer));
        return uuid.toString();
    }

    /**
     * Control if the customer has valid session
     * @param sessionId
     * @return founded customer
     */
    public Customer hasValidSessionForCustomer(String sessionId)
    {
        CustomerSession customerSession = sessionManager.getSessionMap().get(sessionId);
        if(customerSession != null)
          return customerSession.getCustomer();

        /**
         * Means there is no session or session is timed out
         */
        return null;
    }

    /**
     * Runs an infinitive Thread for removing timed out sessions.
     */
    public void runRemoveExpiredSessions()
    {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable runnable = () ->
        {
            sessionMap.entrySet().forEach(customerSessionEntry ->{
                if(customerSessionEntry.getValue().getStartSessionTime().isBefore(LocalTime.now().minusMinutes(sessionTimeOut)))
                /**
                 * Removing timed out session
                 */
                    sessionManager.getSessionMap().remove(customerSessionEntry.getKey());
            } );
        };
        scheduler.scheduleAtFixedRate(runnable,  RemoveExpiredSessionInterval,RemoveExpiredSessionInterval, TimeUnit.SECONDS);
    }
}
