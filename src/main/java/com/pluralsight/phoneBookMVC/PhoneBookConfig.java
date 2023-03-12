package com.pluralsight.phoneBookMVC;

import com.pluralsight.phoneBookMVC.model.Customer;
import com.pluralsight.phoneBookMVC.repository.CustomerRepository;
import com.pluralsight.phoneBookMVC.repository.CustomerRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.*;

@Configuration
public class PhoneBookConfig implements WebMvcConfigurer {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setOrder(0);
        return internalResourceViewResolver;
    }

    @Bean
    public List<Customer> defaultData() {
        List<Customer> data = new ArrayList<>();
        Set<String> numbers1 = new HashSet<>(List.of("+79601232233"));
        Set<String> numbers2 = new HashSet<>(Arrays.asList("+79213215566", "+79213215567"));
        Customer customer1 = new Customer("Gena", numbers1);
        Customer customer2 = new Customer("Ivan", numbers2);
        data.add(customer1);
        data.add(customer2);
        return data;
    }

    @Bean(name = "customerRepository")
    public CustomerRepository customerRepository(List<Customer> defaultData) {
        return new CustomerRepositoryImpl(defaultData);
    }
}
