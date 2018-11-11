package domain;

import infra.SessionManager;

/**
 * This is the main part of our business logic for Customer. This is a Singleton class
 */
public class CustomerManager {
    private SessionManager sessionManager;
    private CustomerRepository customerRepository;

    /**
     * I inject the dependencies in constructor.
     */
    private CustomerManager() {
        sessionManager = SessionManager.getInstance();
        customerRepository = CustomerRepository.getInstance();
    }
    private static CustomerManager customerManager;

    /**
     *
     * @return a singleton instance of CustomerManager
     */
    public static CustomerManager getInstance()
    {
        if(customerManager == null)
            customerManager = new CustomerManager();
        return customerManager;
    }

    /**
     * This method is used for creating a Customer
     * @param customerId
     * @return an added customer into the session
     */
    public String createCustomer(Integer customerId)
    {

        Customer customer = new Customer();
        customer.setId(customerId);

        customerRepository.add(customer);
        /**
         * Creating a session for a customer
         */
        return sessionManager.createSession(customer);


    }

}
