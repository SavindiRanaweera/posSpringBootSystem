package com.ijse.springpos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.springpos.entity.Item;

@Service
public interface ItemService {
    List<Item> getAllItems();
    Item getItemById(Long id);
    Item createItem(Item item);
    Item updateItem(Long id, Item item);
    void deleteItem(Long id);
}
