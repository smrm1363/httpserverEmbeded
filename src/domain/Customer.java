package domain;

import java.io.Serializable;

/**
 * This is our Customer entity
 */
public class Customer implements Serializable {
    private Integer id;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;
        return id.equals(customer.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
