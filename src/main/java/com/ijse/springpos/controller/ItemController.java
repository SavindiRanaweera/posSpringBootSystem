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

import com.ijse.springpos.dto.ItemDto;
import com.ijse.springpos.entity.Item;
import com.ijse.springpos.entity.ItemCategory;
import com.ijse.springpos.service.ItemCategoryService;
import com.ijse.springpos.service.ItemService;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    @GetMapping("/items")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id){
        Item item = itemService.getItemById(id);

        if(item == null){
            return ResponseEntity.status(404).build();
        }else{
            return ResponseEntity.status(200).body(item);
        }
    }

    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody ItemDto itemDto){

        Item item = new Item();

        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());

        ItemCategory itemCategory = itemCategoryService.getItemCategoryById(itemDto.getCategoryId());
        item.setItemCategory(itemCategory);

        Item createItem = itemService.createItem(item);
        return ResponseEntity.status(201).body(createItem);

    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto){

        Item item = new Item();

        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());

        ItemCategory itemCategory = itemCategoryService.getItemCategoryById(itemDto.getCategoryId());
        item.setItemCategory(itemCategory);

        Item updateItem = itemService.updateItem(id,item);

        if(updateItem == null) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(updateItem);
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id){

        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    } 
}
