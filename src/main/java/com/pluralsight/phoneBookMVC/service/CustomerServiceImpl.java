package com.pluralsight.phoneBookMVC.service;

import com.pluralsight.phoneBookMVC.exception.ResourceNotFoundException;
import com.pluralsight.phoneBookMVC.model.Customer;
import com.pluralsight.phoneBookMVC.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepository.addCustomer(customer);
	}

	@Override
	public Customer updateCustomer(String name, String phones) throws ResourceNotFoundException {
		Set<String> phonesSet = Arrays.stream(phones.split(", ")).collect(Collectors.toSet());
		if (phonesSet.size() > 0) {
			return customerRepository.updateCustomer(name, phonesSet);
		} else {
			throw new ResourceNotFoundException(phones);
		}
	}

	@Override
	public Customer getCustomer(String name) throws ResourceNotFoundException {
		if (customerRepository.findByName(name).size() == 0) {
			throw new ResourceNotFoundException(name);
		} else {
			return customerRepository.findByName(name).get(0);
		}
	}

	@Override
	public Customer deleteCustomer(String name) throws ResourceNotFoundException {
		return customerRepository.deleteCustomer(name);
	}
}





