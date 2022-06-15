package com.netcracker.service;

import com.netcracker.dto.*;
import com.netcracker.model.Purchase;
import com.netcracker.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final BookRepository bookRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, CustomerRepository customerRepository,
                           StoreRepository storeRepository, BookRepository bookRepository) {
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
            for (Purchase purchase: purchases) {
                result.add(new CustomerAndStoreDTO(customerRepository.getById(purchase.getCustomer()).getSurname(),
                        storeRepository.getById(purchase.getSeller()).getName()));
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
            for (Purchase purchase: purchases) {
                result.add(new PurchaseAndCustomerDTO(purchase.getDate(),
                        customerRepository.getById(purchase.getCustomer()).getSurname(),
                        customerRepository.getById(purchase.getCustomer()).getDiscount(),
                        bookRepository.getById(purchase.getBook()).getName(), purchase.getQuantity()));
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
            for (Purchase purchase: purchases) {
                result.add(new PurchaseNumberCustomerSurnameDTO(purchase.getPurchaseNumber(),
                        customerRepository.getById(purchase.getCustomer()).getSurname(),  purchase.getDate()));
            }
        } else {
            return new ArrayList<>();
        }
        return result;
    }

    public List<PurchaseAndCustomerOfMonthDTO> getPurchaseAndCustomerAfterMonth(Integer monthNumber){
        ArrayList<Purchase> purchases = (ArrayList<Purchase>) purchaseRepository.findAll();
        ArrayList<PurchaseAndCustomerOfMonthDTO> result = new ArrayList<>();
        if(!purchases.isEmpty()){
            for (Purchase purchase: purchases) {
                if(customerRepository.getById(purchase.getCustomer()).getAreaOfResidence().equals(storeRepository.getById(purchase.getCustomer()).getLocationArea())) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(purchase.getDate());
                    if(cal.get(Calendar.MONTH) >= monthNumber){
                        result.add(new PurchaseAndCustomerOfMonthDTO(purchase.getPurchaseNumber(),
                                customerRepository.getById(purchase.getCustomer()).getSurname(),
                                customerRepository.getById(purchase.getCustomer()).getAreaOfResidence(),
                                purchase.getDate()));
                    }
                }
            }
        } else {
            return new ArrayList<>();
        }
        return result;
    }

    public List<StoresNotInAreaDTO> getStoreNotInArea(String area, Float lowestDiscount, Float highestDiscount){
        ArrayList<Purchase> purchases = (ArrayList<Purchase>) purchaseRepository.findAll();
        ArrayList<StoresNotInAreaDTO> result = new ArrayList<>();
        if(!purchases.isEmpty()){
            for (Purchase purchase: purchases) {
                if(!storeRepository.getById(purchase.getSeller()).getLocationArea().equals(area)) {
                    if(customerRepository.getById(purchase.getCustomer()).getDiscount() >= lowestDiscount &&
                            customerRepository.getById(purchase.getCustomer()).getDiscount() <= highestDiscount){
                        result.add(new StoresNotInAreaDTO(storeRepository.getById(purchase.getSeller()).getName(),
                                storeRepository.getById(purchase.getSeller()).getLocationArea()));
                    }
                }
            }
        } else {
            return new ArrayList<>();
        }
        return result;
    }

    public List<BookSoldInWarehouseAreaDTO> getBookSoldInWarehouseArea(Integer remainAmount){
        ArrayList<Purchase> purchases = (ArrayList<Purchase>) purchaseRepository.findAll();
        ArrayList<BookSoldInWarehouseAreaDTO> result = new ArrayList<>();
        if(!purchases.isEmpty()){
            for (Purchase purchase: purchases) {
                if(storeRepository.getById(purchase.getSeller()).getLocationArea().equals(bookRepository.getById(purchase.getBook()).getWarehouse())) {
                    if(bookRepository.getById(purchase.getBook()).getQuantity() > remainAmount){
                        result.add(new BookSoldInWarehouseAreaDTO(bookRepository.getById(purchase.getBook()).getName(),
                                bookRepository.getById(purchase.getBook()).getWarehouse(),
                                bookRepository.getById(purchase.getBook()).getQuantity(),
                                bookRepository.getById(purchase.getBook()).getPrice()));
                    }
                }
            }
        } else {
            return new ArrayList<>();
        }
        return result;
    }
}
