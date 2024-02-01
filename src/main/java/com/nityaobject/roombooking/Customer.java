
package com.nityaobject.roombooking;

import java.util.Objects;

/**
 *
 * @author ACER
 */
public class Customer 
{
    private String customerName;
    private int customerId;

    public Customer(String customer_name, int customer_id) 
    {
        this.customerName = customer_name;
        this.customerId = customer_id;
    }
    
    public String getCustomer_name() {
        return customerName;
    }

    public void setCustomer_name(String customer_name) {
        this.customerName = customer_name;
    }


    public int getCustomer_id() {
        return customerId;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_id(int customer_id) {
        this.customerId = customer_id;
    }

    @Override
    public String toString() {
        return "Customer{" + "customer_name=" + customerName + ", customer_id=" + customerId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.customerName);
        hash = 89 * hash + this.customerId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.customerId != other.customerId) {
            return false;
        }
        return Objects.equals(this.customerName, other.customerName);
    }
    
    
  
}
