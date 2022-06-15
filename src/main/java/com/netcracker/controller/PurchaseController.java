package com.netcracker.controller;

import com.netcracker.dto.*;
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

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseRepository repository, PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchases")
    public List<Purchase> getAllPurchases() {
        return purchaseService.findAll();
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(purchaseService.getPurchaseById(id));
    }

    @PostMapping("/purchases")
    public Purchase createPurchase(@RequestBody Purchase purchase) {
        return purchaseService.save(purchase);
    }

    @DeleteMapping("/purchases/{id}")
    public ResponseEntity<DeleteResponse> deletePurchase(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        purchaseService.delete(id);
        return ResponseEntity.ok(new DeleteResponse("Purchase with id:" + id + " was deleted"));
    }

    @PatchMapping("/purchases/{id}")
    public ResponseEntity<UpdateResponse> updatePurchaseQuantity(@PathVariable(value = "id") Integer id,
                                                                 @RequestParam(value = "quantity") Integer quantity) throws ResourceNotFoundException {
        purchaseService.updatePurchaseQuantity(id, quantity);
        return ResponseEntity.ok(new UpdateResponse("Purchase with id:" + id +
                " was updated and has a new quantity: " + quantity));
    }

    @PutMapping("/purchases/{id}")
    public ResponseEntity<UpdateResponse> updatePurchase(@PathVariable(value = "id") Integer id,
                                                         @RequestBody Purchase purchaseDescription) throws ResourceNotFoundException {
        purchaseService.updatePurchase(id, purchaseDescription);
        return ResponseEntity.ok(new UpdateResponse("Purchase with id:" + id + " was updated"));
    }

    //вывести все различные месяцы, когда производились покупки
    @GetMapping("/allUniqueMonths")
    public ResponseEntity<HashSet<PurchaseDTO>> getUniqueMonths() {
        HashSet<PurchaseDTO> result = purchaseService.getMonths();
        return ResponseEntity.ok(result);
    }

    //вывести фамилию покупателя и название магазина, где производилась покупка
    @GetMapping("/getSurnameAndStore")
    public ResponseEntity<List<CustomerAndStoreDTO>> getSurnameAndStore() {
        return ResponseEntity.ok(purchaseService.getCustomerAndStore());
    }

    //вывести дату, фамилию покупателя, скидку, название и количество купленных книг
    @GetMapping("/getCustomerAndPurchase")
    public ResponseEntity<List<PurchaseAndCustomerDTO>> getCustomerAndPurchase() {
        return ResponseEntity.ok(purchaseService.getCustomerAndPurchase());
    }

    //вывести номер заказа, фамилию покупателя и дату для покупок, в которых было продано книг на сумму не меньшую чем 60000 руб.
    @GetMapping("/getPurchaseNumberAndCustomerSurnameWithSum")
    public ResponseEntity<List<PurchaseNumberCustomerSurnameDTO>> getPurchaseNumberAndCustomerSurnameWithSum(@RequestParam(value = "sum") Float sum) {
        return ResponseEntity.ok(purchaseService.getPurchaseNumberAndCustomerSurnameWithSum(sum));
    }

    //вывести покупки, сделанные покупателем в своем районе не ранее марта месяца. Вывести фамилию покупателя, район, дату
    @GetMapping("/getPurchaseAndCustomerAfterMonth")
    public ResponseEntity<List<PurchaseAndCustomerOfMonthDTO>> getPurchaseAndCustomerAfterMonth(@RequestParam(value = "monthNumber") Integer monthNumber) {
        return ResponseEntity.ok(purchaseService.getPurchaseAndCustomerAfterMonth(monthNumber));
    }

    //вывести магазины, расположенные в любом районе, кроме Автозаводского, где покупали книги те, у кого скидка от 10 до 15 %
    @GetMapping("/getStoreNotInArea")
    public ResponseEntity<List<StoresNotInAreaDTO>> getStoreNotInArea(@RequestParam(value = "area") String area,
                                                                      @RequestParam(value = "lowestDiscount") Float lowestDiscount,
                                                                      @RequestParam(value = "highestDiscount") Float highestDiscount) {
        return ResponseEntity.ok(purchaseService.getStoreNotInArea(area, lowestDiscount, highestDiscount));
    }

    //вывести данные по покупке книг (название, район складирования, количество), приобретенных в районе складирования и содержащихся в запасе более 10 штук
    @GetMapping("/getBookSoldInWarehouseArea")
    public ResponseEntity<List<BookSoldInWarehouseAreaDTO>> getBookSoldInWarehouseArea(@RequestParam(value = "remainAmount") Integer remainAmount) {
        return ResponseEntity.ok(purchaseService.getBookSoldInWarehouseArea(remainAmount));
    }
}
