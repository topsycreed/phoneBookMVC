package com.pluralsight.phoneBookMVC.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Set;

public class Customer {

    @NotBlank
    @NotEmpty
    private String name;
    @NotEmpty
    private Set<String> phones;

    public Customer() {
    }

    public Customer(String name, Set<String> phones) {
        this.name = name;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phones=" + phones +
                '}';
    }
}
