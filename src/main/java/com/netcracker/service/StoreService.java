package com.netcracker.service;

import com.netcracker.dto.StoreDTO;
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

    public ArrayList<StoreDTO> getStoresFromTwoAreas(String firstArea, String secondArea){
        List<Store> stores = storeRepository.findAllByLocationArea(firstArea);
        stores.addAll(storeRepository.findAllByLocationArea(secondArea));
        ArrayList<StoreDTO> result = new ArrayList<>();
        for (Store store: stores) {
            StoreDTO storeDTO = new StoreDTO(store.getName());
            result.add(storeDTO);
        }
        return result;
    }
}
