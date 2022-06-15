package com.netcracker.service;

import com.netcracker.dto.PurchaseAndCustomerDTO;
import com.netcracker.dto.PurchaseDTO;
import com.netcracker.dto.CustomerAndStoreDTO;
import com.netcracker.dto.PurchaseNumberCustomerSurnameDTO;
import com.netcracker.model.Purchase;
import com.netcracker.repository.BookRepository;
import com.netcracker.repository.CustomerRepository;
import com.netcracker.repository.PurchaseRepository;
import com.netcracker.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final BookRepository bookRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, CustomerRepository customerRepository, StoreRepository storeRepository, BookRepository bookRepository) {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.bookRepository = bookRepository;
    }

    public HashSet<PurchaseDTO> getMonths() {
        List<Purchase> purchases = purchaseRepository.findAll();
        HashSet<PurchaseDTO> result = new HashSet<>();
        for (Purchase purchase: purchases) {
            PurchaseDTO purchaseDTO = new PurchaseDTO(purchase.getDate());
            result.add(purchaseDTO);
        }
        return result;
    }

    public List<CustomerAndStoreDTO> getCustomerAndStore() {
        ArrayList<Purchase> purchases = (ArrayList<Purchase>) purchaseRepository.findAll();
        ArrayList<CustomerAndStoreDTO> result = new ArrayList<>();
        if(!purchases.isEmpty()){
            for (Purchase purchase:
                 purchases) {
                result.add(new CustomerAndStoreDTO(customerRepository.getById(purchase.getCustomer()).getSurname(), storeRepository.getById(purchase.getSeller()).getName()));
            }
        } else {
            return new ArrayList<>();
        }
        return result;
    }

    public List<PurchaseAndCustomerDTO> getCustomerAndPurchase() {
        ArrayList<Purchase> purchases = (ArrayList<Purchase>) purchaseRepository.findAll();
        ArrayList<PurchaseAndCustomerDTO> result = new ArrayList<>();
        if(!purchases.isEmpty()){
            for (Purchase purchase:
                    purchases) {
                result.add(new PurchaseAndCustomerDTO(purchase.getDate(), customerRepository.getById(purchase.getCustomer()).getSurname(), customerRepository.getById(purchase.getCustomer()).getDiscount(), bookRepository.getById(purchase.getBook()).getName(), purchase.getQuantity()));
            }
        } else {
            return new ArrayList<>();
        }
        return result;
    }

    public List<PurchaseNumberCustomerSurnameDTO> getPurchaseNumberAndCustomerSurnameWithSum(Float sum) {
        ArrayList<Purchase> purchases = (ArrayList<Purchase>) purchaseRepository.findAllByPurchaseAmountIsGreaterThanEqual(sum);
        ArrayList<PurchaseNumberCustomerSurnameDTO> result = new ArrayList<>();
        if(!purchases.isEmpty()){
            for (Purchase purchase:
                    purchases) {
                result.add(new PurchaseNumberCustomerSurnameDTO(purchase.getPurchaseNumber(), customerRepository.getById(purchase.getCustomer()).getSurname(),  purchase.getDate()));
            }
        } else {
            return new ArrayList<>();
        }
        return result;
    }
}
