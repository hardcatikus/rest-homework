package com.netcracker.controller;

import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Purchase;
import com.netcracker.repository.PurchaseRepository;
import com.netcracker.response.DeleteResponse;
import com.netcracker.response.UpdateResponse;
import com.netcracker.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class PurchaseController {

    @Autowired
    PurchaseRepository repository;
    PurchaseService purchaseService;

    @GetMapping("/purchases")
    public List<Purchase> getAllPurchases(){
        return repository.findAll();
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Purchase was not found for id:" + id));
        return ResponseEntity.ok(purchase);
    }

    @PostMapping("/purchases")
    public Purchase createPurchase(@RequestBody Purchase purchase) {
        return repository.save(purchase);
    }

    @DeleteMapping("/purchases/{id}")
    public ResponseEntity<DeleteResponse> deletePurchase(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Purchase purchase = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Purchase was not found for id:" + id));
        repository.delete(purchase);
        return ResponseEntity.ok(new DeleteResponse("Purchase with id:" + id + " was deleted"));
    }

    @PatchMapping("/purchases/{id}/{quantity}")
    public ResponseEntity<UpdateResponse> updatePurchaseQuantity(@PathVariable(value = "id") Integer id,
                                                         @RequestBody Integer quantity) throws ResourceNotFoundException{
        Purchase purchase = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Purchase was not found for id:" + id));
        purchase.setQuantity(quantity);
        repository.save(purchase);
        return ResponseEntity.ok(new UpdateResponse("Purchase with id:" + id +
                " was updated and has a new quantity: " + quantity));
    }

    @PutMapping("/purchases/{id}")
    public ResponseEntity<UpdateResponse> updatePurchase(@PathVariable(value = "id") Integer id,
                                                     @RequestBody Purchase purchaseDescription) throws ResourceNotFoundException{
        Purchase purchase = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Purchase was not found for id:" + id));

        purchase.setDate(purchaseDescription.getDate());
        purchase.setSeller(purchaseDescription.getSeller());
        purchase.setCustomer(purchaseDescription.getCustomer());
        purchase.setBook(purchaseDescription.getBook());
        purchase.setQuantity(purchaseDescription.getQuantity());
        purchase.setPurchaseAmount(purchaseDescription.getPurchaseAmount());

        repository.save(purchase);
        return ResponseEntity.ok(new UpdateResponse("Purchase with id:" + id + " was updated"));
    }

}
