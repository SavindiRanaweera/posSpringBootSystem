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

import com.ijse.springpos.dto.StockDto;
import com.ijse.springpos.entity.Item;
import com.ijse.springpos.entity.Stock;
import com.ijse.springpos.service.ItemService;
import com.ijse.springpos.service.StockService;

@RestController
@CrossOrigin(origins = "*")
public class StockController {
    
    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/stocks")
    public List<Stock> getAllStock(){
        return stockService.getAllStock();
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id){
        Stock stock = stockService.getStockById(id);

        if(stock == null){
            return ResponseEntity.status(404).build();
        }else{
            return ResponseEntity.status(200).body(stock);
        }
    }

    @PostMapping("/stocks")
    public ResponseEntity<Stock> createStock(@RequestBody StockDto stockDto){

        Stock stock = new Stock();
        stock.setQuantity(stockDto.getQuantity());

        Item item = itemService.getItemById(stockDto.getItemId());
        stock.setItem(item);

        Stock cretaStock = stockService.createStock(stock);
        return ResponseEntity.status(201).body(cretaStock);

    }

    @PutMapping("/stocks/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody StockDto stockDto){

        Stock stock = new Stock();
        stock.setQuantity(stockDto.getQuantity());

        Item item = itemService.getItemById(stockDto.getItemId());
        stock.setItem(item);

        Stock updateStock = stockService.updateStock(id, stock);

        if(updateStock == null){
            return ResponseEntity.status(404).build();
        }else{
            return ResponseEntity.status(200).body(updateStock);
        }
    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id){

        stockService.deleteStock(id);

        return ResponseEntity.ok().build();
    }
}
