package domain;

import java.io.Serializable;
import java.util.List;

import java.util.concurrent.ConcurrentHashMap;

public class Bet implements Serializable{
    private Integer id;
    private ConcurrentHashMap<Customer,Integer> customerBetOfferMap;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public ConcurrentHashMap<Customer, Integer> getCustomerBetOfferMap() {
        return customerBetOfferMap;
    }

    public void setCustomerBetOfferMap(ConcurrentHashMap<Customer, Integer> customerBetOfferMap) {
        this.customerBetOfferMap = customerBetOfferMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bet bet = (Bet) o;

        if (!id.equals(bet.id)) return false;
        return customerBetOfferMap != null ? customerBetOfferMap.equals(bet.customerBetOfferMap) : bet.customerBetOfferMap == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
