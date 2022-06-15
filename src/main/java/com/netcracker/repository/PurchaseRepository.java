package com.netcracker.repository;

import com.netcracker.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    List<Purchase> findAllByPurchaseAmountIsGreaterThanEqual(Float sum);

    List<Purchase> findAllByOrderByDateDesc();
}
