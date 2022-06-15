package com.netcracker.controller;

import com.netcracker.dto.StoreDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Store;
import com.netcracker.response.DeleteResponse;
import com.netcracker.response.UpdateResponse;
import com.netcracker.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/stores")
    public List<Store> getAllStores() {
        return storeService.findAll();
    }

    @GetMapping("/stores/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @PostMapping("/stores")
    public Store createStore(@RequestBody Store store) {
        return storeService.save(store);
    }

    @DeleteMapping("/stores/{id}")
    public ResponseEntity<DeleteResponse> deleteCustomer(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        storeService.delete(id);
        return ResponseEntity.ok(new DeleteResponse("Store with id:" + id + " was deleted"));
    }

    @PatchMapping("/stores/{id}")
    public ResponseEntity<UpdateResponse> updateStoreName(@PathVariable(value = "id") Integer id,
                                                          @RequestParam(value = "name") String name) throws ResourceNotFoundException {
        storeService.updateStoreSurname(id, name);
        return ResponseEntity.ok(new UpdateResponse("Store with id:" + id +
                " was updated and has a new name: " + name));
    }

    @PutMapping("/stores/{id}")
    public ResponseEntity<UpdateResponse> updateStore(@PathVariable(value = "id") Integer id,
                                                      @RequestBody Store storeDescription) throws ResourceNotFoundException {
        storeService.updateStore(id, storeDescription);
        return ResponseEntity.ok(new UpdateResponse("Store with id:" + id + " was updated"));
    }

    //вывести названия магазинов из двух районов
    @GetMapping("/storesFromTwoAreas")
    public ResponseEntity<ArrayList<StoreDTO>> getStoresFromTwoAreas(@RequestParam(value = "firstArea") String firstArea,
                                                                     @RequestParam(value = "secondArea") String secondArea) {
        return ResponseEntity.ok(storeService.getStoresFromTwoAreas(firstArea, secondArea));
    }
}
