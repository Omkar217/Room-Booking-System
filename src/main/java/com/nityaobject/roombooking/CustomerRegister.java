
package com.nityaobject.roombooking;

import java.util.HashMap;
import java.util.Map;

public class CustomerRegister {

    private Map<Integer, Customer> customerIdToCustomerMap;

    public Map<Integer, Customer> getCustomerIdToCustomerMap() 
    {
        return customerIdToCustomerMap;
    }

    public CustomerRegister() 
    {
        customerIdToCustomerMap = new HashMap<>();
    }    
    
    public void addCustomer(Integer customerId, Customer customer)
    {
        customerIdToCustomerMap.put(customerId,customer); 
    }
    
    public void removeCustomer(Integer customerId) throws InvalidCustomer
    {
        if(customerIdToCustomerMap.containsKey(customerId))
        {
            customerIdToCustomerMap.remove(customerId);
        }
        else
        {
            throw new InvalidCustomer("Customer doesn't exists here ");
        }
    }
    
       
}
