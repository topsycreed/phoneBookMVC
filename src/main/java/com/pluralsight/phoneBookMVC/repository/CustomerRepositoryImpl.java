package com.pluralsight.phoneBookMVC.repository;

import com.pluralsight.phoneBookMVC.exception.ResourceNotFoundException;
import com.pluralsight.phoneBookMVC.model.Customer;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerRepositoryImpl implements CustomerRepository {

    private List<Customer> data;

    /**
     * no args constructor
     */
    public CustomerRepositoryImpl() {
        this(new ArrayList<>());
    }

    /**
     * this constructor allows to inject initial data to the repository
     *
     * @param data
     */
    public CustomerRepositoryImpl(List<Customer> data) {
        this.data = new ArrayList<>(data);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(this.data);
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> found = this.data.stream().filter(s -> s.getName().equals(name)).collect(Collectors.toList());
        return found;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        List<Customer> customers = findByName(customer.getName());
        if (customers.size() == 0) {
            this.data.add(customer);
            return customer;
        } else {
            Customer exsistingCustomer = customers.get(0);
            exsistingCustomer.getPhones().addAll(customer.getPhones());
            return exsistingCustomer;
        }
    }

    @Override
    public Customer updateCustomer(String name, Set<String> phones) throws ResourceNotFoundException {
        List<Customer> customers = findByName(name);
        if (customers.size() == 0) {
            throw new ResourceNotFoundException(name);
        } else {
            Customer customer = customers.get(0);
            customer.getPhones().addAll(phones);
            return customer;
        }
    }

    @Override
    public Customer deleteCustomer(String name) throws ResourceNotFoundException {
        List<Customer> customers = findByName(name);
        if (customers.size() == 0) {
            throw new ResourceNotFoundException(name);
        } else {
            Customer customer = customers.get(0);
            this.data.remove(customer);
            return customer;
        }
    }
}
