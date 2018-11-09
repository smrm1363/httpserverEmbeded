package infra;

import domain.Customer;
import domain.CustomerSession;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private Map<String , CustomerSession> sessionMap;
    private static SessionManager sessionManager;

    private SessionManager() {
        this.sessionMap = new ConcurrentHashMap<>();
    }
    public static SessionManager getInstance()
    {
        if(sessionManager == null)
            sessionManager = new SessionManager() ;
        return sessionManager;
    }

    public Map<String, CustomerSession> getSessionMap() {
        return sessionMap;
    }
    public String createSession(Customer customer)
    {
        UUID uuid = UUID.randomUUID();
        sessionMap.put(uuid.toString(),new CustomerSession(customer));
        return uuid.toString();
    }
}
