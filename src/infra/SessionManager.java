package infra;

import domain.Customer;
import domain.CustomerSession;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is our session manager. This is a Singleton class
 */
public class SessionManager {
    private Map<String , CustomerSession> sessionMap;
    private static SessionManager sessionManager;

    private SessionManager() {
        /**
         * I use ConcurrentHashMap for being thread safe
         */
        this.sessionMap = new ConcurrentHashMap<>();
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
}
