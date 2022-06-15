package com.netcracker.controller;

import com.netcracker.dto.PurchaseAndCustomerDTO;
import com.netcracker.dto.PurchaseDTO;
import com.netcracker.dto.CustomerAndStoreDTO;
import com.netcracker.dto.PurchaseNumberCustomerSurnameDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Purchase;
import com.netcracker.repository.PurchaseRepository;
import com.netcracker.response.DeleteResponse;
import com.netcracker.response.UpdateResponse;
import com.netcracker.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class PurchaseController {

    private final PurchaseRepository repository;
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseRepository repository, PurchaseService purchaseService) {
        this.repository = repository;
        this.purchaseService = purchaseService;
    }

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

    @PatchMapping("/purchases/{id}")
    public ResponseEntity<UpdateResponse> updatePurchaseQuantity(@PathVariable(value = "id") Integer id,
                                                         @RequestParam(value = "quantity") Integer quantity) throws ResourceNotFoundException{
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

    //вывести все различные месяцы, когда производились покупки
    @GetMapping("/allUniqueMonths")
    public ResponseEntity<HashSet<PurchaseDTO>> getUniqueMonths(){
        HashSet<PurchaseDTO> result = purchaseService.getMonths();
        return ResponseEntity.ok(result);
    }

    //вывести фамилию покупателя и название магазина, где производилась покупка
    @GetMapping("/getSurnameAndStore")
    public ResponseEntity<List<CustomerAndStoreDTO>> getSurnameAndStore(){
        return ResponseEntity.ok(purchaseService.getCustomerAndStore());
    }

    //вывести дату, фамилию покупателя, скидку, название и количество купленных книг
    @GetMapping("/getCustomerAndPurchase")
    public ResponseEntity<List<PurchaseAndCustomerDTO>> getCustomerAndPurchase(){
        return ResponseEntity.ok(purchaseService.getCustomerAndPurchase());
    }

    //вывести номер заказа, фамилию покупателя и дату для покупок, в которых было продано книг на сумму не меньшую чем 60000 руб.
    @GetMapping("/getPurchaseNumberAndCustomerSurnameWithSum")
    public ResponseEntity<List<PurchaseNumberCustomerSurnameDTO>> getPurchaseNumberAndCustomerSurnameWithSum(@RequestParam(value = "sum") Float sum){
        return ResponseEntity.ok(purchaseService.getPurchaseNumberAndCustomerSurnameWithSum(sum));
    }
}
