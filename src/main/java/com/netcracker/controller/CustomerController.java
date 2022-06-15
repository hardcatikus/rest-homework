package com.netcracker.controller;

import com.netcracker.dto.CustomerDTO;
import com.netcracker.dto.CustomerSurnameDiscountDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Customer;
import com.netcracker.repository.CustomerRepository;
import com.netcracker.response.DeleteResponse;
import com.netcracker.response.UpdateResponse;
import com.netcracker.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class CustomerController {

    private final CustomerRepository repository;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository repository, CustomerService customerService) {
        this.repository = repository;
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return repository.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Customer customer = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer was not found for id:" + id));
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<DeleteResponse> deleteCustomer(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Customer customer = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer was not found for id:" + id));
        repository.delete(customer);
        return ResponseEntity.ok(new DeleteResponse("Customer with id:" + id + " was deleted"));
    }

    @PatchMapping("/customers/{id}")
    public ResponseEntity<UpdateResponse> updateCustomerSurname(@PathVariable(value = "id") Integer id,
                                                                @RequestParam(value = "surname") String surname) throws ResourceNotFoundException{
        Customer customer = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer was not found for id:" + id));
        customer.setSurname(surname);
        repository.save(customer);
        return ResponseEntity.ok(new UpdateResponse("Customer with id:" + id +
                " was updated and has a new surname: " + surname));
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<UpdateResponse> updateCustomer(@PathVariable(value = "id") Integer id,
                                                         @RequestBody Customer customerDescription) throws ResourceNotFoundException{
        Customer customer = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer was not found for id:" + id));

        customer.setSurname(customerDescription.getSurname());
        customer.setAreaOfResidence(customerDescription.getAreaOfResidence());
        customer.setDiscount(customerDescription.getDiscount());

        repository.save(customer);
        return ResponseEntity.ok(new UpdateResponse("Customer with id:" + id + " was updated"));
    }

    //вывести все различные районы, в которых проживают покупатели
    @GetMapping("/allUniqueAreas")
    public ResponseEntity<HashSet<CustomerDTO>> getCustomerAreas(){
        return ResponseEntity.ok(customerService.getAreas());
    }

    //вывести информацию о фамилиях и размере скидки всех покупателей, проживающих в определенном районе
    @GetMapping("/getCustomersFromOneArea")
    public ResponseEntity<ArrayList<CustomerSurnameDiscountDTO>> getCustomersFromOneArea(@RequestParam(value = "areaOfResidence") String areaOfResidence){
        return ResponseEntity.ok(customerService.getCustomersFromOneArea(areaOfResidence));
    }
}
