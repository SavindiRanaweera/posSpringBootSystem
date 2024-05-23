package com.ijse.springpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.springpos.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
    
}
