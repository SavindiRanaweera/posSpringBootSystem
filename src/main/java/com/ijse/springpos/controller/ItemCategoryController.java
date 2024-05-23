package com.ijse.springpos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.springpos.entity.ItemCategory;
import com.ijse.springpos.service.ItemCategoryService;

@RestController
@CrossOrigin(origins = "*")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

     @GetMapping("/categories")
    public List<ItemCategory> getAllCategories() {
        return itemCategoryService.getAllItemCategories();
    }

    @GetMapping("/categories/{id}") 
    public ResponseEntity<ItemCategory> getItemCategoryById(@PathVariable Long id) {

        ItemCategory itemCategory = itemCategoryService.getItemCategoryById(id);

        if(itemCategory == null){
            return ResponseEntity.status(404).build();
        }else{
            return ResponseEntity.status(200).body(itemCategory);
        }
        
    }

    @PostMapping("/categories")
    public ResponseEntity<ItemCategory> createItemCategory(@RequestBody ItemCategory itemCategory) {

        ItemCategory createCategories = itemCategoryService.createItemCategory(itemCategory);

        return ResponseEntity.status(201).body(createCategories);
    }
    
    @PutMapping("/categories/{id}")
    public ResponseEntity<ItemCategory> updateItemCategory(@PathVariable Long id, @RequestBody ItemCategory itemCategory){

        ItemCategory updateCategories = itemCategoryService.updateItemCategory(id, itemCategory);

        if(updateCategories == null) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(updateCategories);
        }

    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteItemCategory(@PathVariable Long id){

        itemCategoryService.deleteItemCategory(id);
        return ResponseEntity.ok().build();
    }

}
