package com.netcracker.service;

import com.netcracker.dto.CustomerDTO;
import com.netcracker.dto.CustomerSurnameDiscountDTO;
import com.netcracker.model.Customer;
import com.netcracker.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public HashSet<CustomerDTO> getAreas() {
        List<Customer> customers = customerRepository.findAll();
        HashSet<CustomerDTO> result = new HashSet<>();
        for (Customer customer: customers) {
            CustomerDTO customerDTO = new CustomerDTO(customer.getAreaOfResidence());
            result.add(customerDTO);
        }
        return result;
    }

    public ArrayList<CustomerSurnameDiscountDTO> getCustomersFromOneArea(String areaOfResidence){
        List<Customer> customers = customerRepository.findAllByAreaOfResidence(areaOfResidence);
        ArrayList<CustomerSurnameDiscountDTO> result = new ArrayList<>();
        for (Customer customer: customers) {
            CustomerSurnameDiscountDTO customerDTO = new CustomerSurnameDiscountDTO(customer.getSurname(),customer.getDiscount());
            result.add(customerDTO);
        }
        return result;
    }
}
