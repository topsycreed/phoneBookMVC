package com.pluralsight.phoneBookMVC.service;

import com.pluralsight.phoneBookMVC.exception.ResourceNotFoundException;
import com.pluralsight.phoneBookMVC.model.Customer;

import java.util.List;

public interface CustomerService {

	List<Customer> getCustomers();

	Customer addCustomer(Customer customer);

	Customer updateCustomer(String name, String phones) throws ResourceNotFoundException;

	Customer getCustomer(String name) throws ResourceNotFoundException;

	Customer deleteCustomer(String name) throws ResourceNotFoundException;
	
}
