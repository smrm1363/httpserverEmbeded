package domain;

import java.util.Map;

/**
 * This is a repository of Customer. This is Singleton
 */
public class CustomerRepository extends AbstractRepository<Customer>{

    private static CustomerRepository customerRepository;
    private CustomerRepository() {


    }

    public static CustomerRepository getInstance()
    {
        if(customerRepository==null)
            customerRepository = new CustomerRepository();
        return customerRepository;
    }



    public Map getCustomerSet() {
        return entityMap;
    }


}
