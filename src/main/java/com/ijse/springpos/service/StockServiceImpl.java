package com.ijse.springpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.springpos.entity.Stock;
import com.ijse.springpos.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService{
    
    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> getAllStock(){
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockById(Long id){
        return stockRepository.findById(id).orElse(null);
    }

    @Override
    public Stock createStock(Stock stock){
        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(Long id, Stock stock){
        Stock existStock = stockRepository.findById(id).orElse(null);

        if(existStock == null){
            return null;
        }

        existStock.setQuantity(stock.getQuantity());

        return stockRepository.save(existStock);
    }

    @Override
    public void deleteStock(Long id){
        stockRepository.deleteById(id);
    }
}
