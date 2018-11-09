package domain;

import java.time.LocalTime;

public class CustomerSession {
    ;
    private LocalTime startSessionTime;
    private Customer customer;

    public CustomerSession(Customer customer) {

        this.customer = customer;
        this.startSessionTime = LocalTime.now();
    }



    public LocalTime getStartSessionTime() {
        return startSessionTime;
    }

    private void setStartSessionTime(LocalTime startSessionTime) {
        this.startSessionTime = startSessionTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
