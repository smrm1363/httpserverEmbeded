package domain;

import com.sun.xml.internal.bind.v2.TODO;
import infra.SessionManager;

import java.time.LocalTime;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class BetManager {
    private SessionManager sessionManager;
    private CustomerRepository customerRepository;
    private BetRepository betRepository;
    private BetManager() {
        sessionManager = SessionManager.getInstance();
        customerRepository = CustomerRepository.getInstance();
        betRepository = BetRepository.getInstance();
    }
    private static BetManager betManager;
    public static BetManager getInstance()
    {
        if(betManager == null)
            betManager = new BetManager();
        return betManager;
    }
    public void createBet(String sessionId,Integer betId,Integer stack) throws Exception {
        Customer customer = hasValidSessionForCustomer(sessionId);
        if(customer == null)
            throw new Exception("There is not any valid session");
        Bet bet = new Bet();
        bet.setId(betId);
        Optional<Bet> betOptional = betRepository.find(bet);

        if(betOptional.isPresent())
             bet.setCustomerBetOfferMap(betOptional.get().getCustomerBetOfferMap());
        else
            bet.setCustomerBetOfferMap(new LimitedConcurrentHashMap());
        Optional<Integer> integerOptional = Optional.ofNullable(bet.getCustomerBetOfferMap().get(customer));
        final Integer[] maxStack = {stack};
        integerOptional.ifPresent(lastValue -> maxStack[0] =lastValue.compareTo(maxStack[0])>0? lastValue :maxStack[0]);
        bet.getCustomerBetOfferMap().put(customer,maxStack[0]);
        betRepository.add(bet);

    }
    private Customer hasValidSessionForCustomer(String sessionId)
    {
        CustomerSession customerSession = sessionManager.getSessionMap().get(sessionId);
        if(customerSession != null)
        {
            if(customerSession.getStartSessionTime().isBefore(LocalTime.now().minusMinutes(10)))
                sessionManager.getSessionMap().remove(sessionId);
            else
                return customerSession.getCustomer();
        }
        /**
         * Means there is no session or session is timed out
         */
        return null;
    }


}
