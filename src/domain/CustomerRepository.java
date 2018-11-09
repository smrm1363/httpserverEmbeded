package domain;

import java.util.Map;

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
