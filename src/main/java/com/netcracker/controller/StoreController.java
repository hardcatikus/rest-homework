package com.netcracker.controller;

import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Store;
import com.netcracker.repository.StoreRepository;
import com.netcracker.response.DeleteResponse;
import com.netcracker.response.UpdateResponse;
import com.netcracker.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class StoreController {

    @Autowired
    StoreRepository repository;
    StoreService storeService;

    @GetMapping("/stores")
    public List<Store> getAllStores(){
        return repository.findAll();
    }

    @GetMapping("/stores/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Store store = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Store was not found for id:" + id));
        return ResponseEntity.ok(store);
    }

    @PostMapping("/stores")
    public Store createStore(@RequestBody Store store) {
        return repository.save(store);
    }

    @DeleteMapping("/stores/{id}")
    public ResponseEntity<DeleteResponse> deleteCustomer(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Store store = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Store was not found for id:" + id));
        repository.delete(store);
        return ResponseEntity.ok(new DeleteResponse("Store with id:" + id + " was deleted"));
    }

    @PatchMapping("/stores/{id}/{name}")
    public ResponseEntity<UpdateResponse> updateStoreName(@PathVariable(value = "id") Integer id,
                                                                @RequestBody String name) throws ResourceNotFoundException{
        Store store = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Store was not found for id:" + id));
        store.setName(name);
        repository.save(store);
        return ResponseEntity.ok(new UpdateResponse("Store with id:" + id +
                " was updated and has a new name: " + name));
    }

    @PutMapping("/stores/{id}")
    public ResponseEntity<UpdateResponse> updateStore(@PathVariable(value = "id") Integer id,
                                                         @RequestBody Store storeDescription) throws ResourceNotFoundException{
        Store store = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Store was not found for id:" + id));

        store.setName(storeDescription.getName());
        store.setLocationArea(storeDescription.getLocationArea());
        store.setCommissionFee(storeDescription.getCommissionFee());

        repository.save(store);
        return ResponseEntity.ok(new UpdateResponse("Store with id:" + id + " was updated"));
    }
}
