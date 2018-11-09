package domain;

import infra.SessionManager;

import java.util.Optional;

public class CustomerManager {
    private SessionManager sessionManager;
    private CustomerRepository customerRepository;
    private CustomerManager() {
        sessionManager = SessionManager.getInstance();
        customerRepository = CustomerRepository.getInstance();
    }
    private static CustomerManager customerManager;
    public static CustomerManager getInstance()
    {
        if(customerManager == null)
            customerManager = new CustomerManager();
        return customerManager;
    }
    public String createCustomer(Integer customerId)
    {

        Customer customer = new Customer();
        customer.setId(customerId);
        Optional<Customer> customerOptional = customerRepository.find(customer);
        if(customerOptional.isPresent())
             customer.setBetOfferList(customerOptional.get().getBetOfferList());
        else
            customer.setBetOfferList(null);
        customerRepository.add(customer);
        return sessionManager.createSession(customer);


    }

}
