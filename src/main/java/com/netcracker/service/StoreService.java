package com.netcracker.service;

import com.netcracker.dto.StoreDTO;
import com.netcracker.exception.ResourceNotFoundException;
import com.netcracker.model.Store;
import com.netcracker.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public ArrayList<StoreDTO> getStoresFromTwoAreas(String firstArea, String secondArea) {
        List<Store> stores = storeRepository.findAllByLocationArea(firstArea);
        stores.addAll(storeRepository.findAllByLocationArea(secondArea));
        ArrayList<StoreDTO> result = new ArrayList<>();
        for (Store store : stores) {
            StoreDTO storeDTO = new StoreDTO(store.getName());
            result.add(storeDTO);
        }
        return result;
    }

    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    public Store getStoreById(Integer id) throws ResourceNotFoundException {
        Store store = storeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Store was not found for id:" + id));
        return store;
    }

    public Store save(Store store) {
        return storeRepository.save(store);
    }

    public void delete(Integer id) throws ResourceNotFoundException {
        Store store = storeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Store was not found for id:" + id));
        storeRepository.delete(store);
    }

    public void updateStoreSurname(Integer id, String name) throws ResourceNotFoundException {
        Store store = storeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Store was not found for id:" + id));
        store.setName(name);
        storeRepository.save(store);
    }

    public void updateStore(Integer id, Store storeDescription) throws ResourceNotFoundException {
        Store store = storeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Store was not found for id:" + id));

        store.setName(storeDescription.getName());
        store.setLocationArea(storeDescription.getLocationArea());
        store.setCommissionFee(storeDescription.getCommissionFee());

        storeRepository.save(store);
    }
}
