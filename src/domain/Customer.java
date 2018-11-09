package domain;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {
    private Integer id;
  //  private List<CustomerBetOffer> betOfferList;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public List<CustomerBetOffer> getBetOfferList() {
//        return betOfferList;
//    }
//
//    public void setBetOfferList(List<CustomerBetOffer> betOfferList) {
//        this.betOfferList = betOfferList;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;
        return id.equals(customer.id);
        //if (!id.equals(customer.id)) return false;
       // return betOfferList != null ? betOfferList.equals(customer.betOfferList) : customer.betOfferList == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
