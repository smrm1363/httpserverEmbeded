package domain;

import java.io.Serializable;

/**
 * This is Bet entity class
 */
public class Bet implements Serializable{
    private Integer id;
    /**
     * This is a custom Map which has limited spaces. It keeps bet offer of customers
     */
    private LimitedConcurrentHashMap customerBetOfferMap;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public LimitedConcurrentHashMap getCustomerBetOfferMap() {
        return customerBetOfferMap;
    }

    public void setCustomerBetOfferMap(LimitedConcurrentHashMap customerBetOfferMap) {
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
