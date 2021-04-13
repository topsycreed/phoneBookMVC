package com.pluralsight.phoneBookMVC.repository;

import com.pluralsight.phoneBookMVC.exception.ResourceNotFoundException;
import com.pluralsight.phoneBookMVC.model.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerRepository {
    /**
     * @return all repository records "{name: [phone1, phone2]}"
     */
    List<Customer> findAll();

    List<Customer> findByName(String name);

    Customer addCustomer(Customer customer);

    Customer updateCustomer(String name, Set<String> phones) throws ResourceNotFoundException;

    Customer deleteCustomer(String name) throws ResourceNotFoundException;
}
