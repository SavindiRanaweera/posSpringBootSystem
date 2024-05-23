package com.ijse.springpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijse.springpos.entity.ItemCategory;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory,Long>{
    
}
