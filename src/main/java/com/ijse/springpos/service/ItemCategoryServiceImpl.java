package com.ijse.springpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.springpos.entity.ItemCategory;
import com.ijse.springpos.repository.ItemCategoryRepository;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService{
    
    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<ItemCategory> getAllItemCategories(){
        return itemCategoryRepository.findAll();
    }

    @Override
    public ItemCategory getItemCategoryById(Long id){
        return itemCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public ItemCategory createItemCategory(ItemCategory itemCategory){
        return itemCategoryRepository.save(itemCategory);
    }

    @Override
    public ItemCategory updateItemCategory(Long id, ItemCategory itemCategory){
        ItemCategory exisItemCategory = itemCategoryRepository.findById(id).orElse(null);

        if(exisItemCategory == null){
            return null;
        }

        exisItemCategory.setName(itemCategory.getName());

        return itemCategoryRepository.save(exisItemCategory);
    }

    @Override
    public void deleteItemCategory(Long id){
        itemCategoryRepository.deleteById(id);
    }
}
