package domain;

import infra.SessionManager;

import java.time.LocalTime;
import java.util.Optional;

/**
 * This is the main part of our business logic for Bet. This is a Singleton class
 */
public class BetManager {
    private SessionManager sessionManager;
    private CustomerRepository customerRepository;
    private BetRepository betRepository;

    /**
     * I inject the dependencies in constructor.
     */
    private BetManager() {
        sessionManager = SessionManager.getInstance();
        customerRepository = CustomerRepository.getInstance();
        betRepository = BetRepository.getInstance();
    }
    private static BetManager betManager;

    /**
     *
     * @return a singleton instance of BetManager
     */
    public static BetManager getInstance()
    {
        if(betManager == null)
            betManager = new BetManager();
        return betManager;
    }

    /**
     * This method is used for creating a Bet
     * @param sessionId is the customer Session ID
     * @param betId is The Id of the Bet
     * @param stack is the value of customer offered money
     * @throws Exception when the session Id is not valid
     */
    public void createBet(String sessionId,Integer betId,Integer stack) throws Exception {
        Customer customer = sessionManager.hasValidSessionForCustomer(sessionId);
        if(customer == null)
            throw new Exception("There is not any valid session");
        Bet bet = new Bet();
        bet.setId(betId);
        Optional<Bet> betOptional = betRepository.find(bet);

        /**
         * Merging the old offers to new offer
         */
        if(betOptional.isPresent())
             bet.setCustomerBetOfferMap(betOptional.get().getCustomerBetOfferMap());
        else
            bet.setCustomerBetOfferMap(new LimitedConcurrentHashMap());
        Optional<Integer> integerOptional = Optional.ofNullable(bet.getCustomerBetOfferMap().get(customer));
        final Integer[] maxStack = {stack};
        /**
         * Put the highest value as the customer offer
         */
        integerOptional.ifPresent(lastValue -> maxStack[0] =lastValue.compareTo(maxStack[0])>0? lastValue :maxStack[0]);
        bet.getCustomerBetOfferMap().put(customer,maxStack[0]);
        betRepository.add(bet);

    }

    /**
     * This method finds the offers of a Bet, orders by higher offers
     * @param betId
     * @return a String of offers
     * @throws Exception
     */
    public String getHighestStack(Integer betId) throws Exception {
        Bet bet = new Bet();
        bet.setId(betId);
        bet = betRepository.find(bet).orElse(null);
        StringBuilder stringBuilder = new StringBuilder();
        if(bet!=null)
        {
            if((bet.getCustomerBetOfferMap().size() >0))
            {
                /**
                 * Generating the output string order by offers values
                 */
                bet.getCustomerBetOfferMap().entrySet().stream()
                        .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                        .forEach(customerIntegerEntry -> stringBuilder
                                .append(","+customerIntegerEntry.getKey().getId()+"="+customerIntegerEntry.getValue()));
                stringBuilder.deleteCharAt(0);
            }
        }
        return stringBuilder.toString();
    }




}
