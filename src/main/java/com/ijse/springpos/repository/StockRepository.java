package com.ijse.springpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijse.springpos.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{
    
}
