package com.pluralsight.phoneBookMVC.controller;

import com.pluralsight.phoneBookMVC.exception.ResourceNotFoundException;
import com.pluralsight.phoneBookMVC.model.Customer;
import com.pluralsight.phoneBookMVC.service.CustomerService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customers/{name}")
    public Customer getCustomer(@PathVariable String name) throws ResourceNotFoundException {
        return customerService.getCustomer(name);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/customers")
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/customers/{name}")
    public Customer addPhoneToExistingCustomer(@PathVariable String name, @NotEmpty @RequestParam(name = "phones") String phones) throws ResourceNotFoundException {
        return customerService.updateCustomer(name, phones);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/customers/{name}")
    public Customer deleteCustomer(@PathVariable String name) throws ResourceNotFoundException {
        return customerService.deleteCustomer(name);
    }
}
