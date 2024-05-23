package com.ijse.springpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.springpos.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    
}
