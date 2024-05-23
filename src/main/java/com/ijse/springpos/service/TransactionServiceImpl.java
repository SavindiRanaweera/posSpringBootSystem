package com.ijse.springpos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.springpos.entity.Item;
import com.ijse.springpos.entity.Stock;
import com.ijse.springpos.entity.Transaction;
import com.ijse.springpos.repository.ItemRepository;
import com.ijse.springpos.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{
    
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockService stockService;

    @Override
    public List<Transaction> getALLTransactions(){
        return transactionRepository.findAll();  
    }

    @Override
    public Transaction getTransactionById(Long id){
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public Transaction createInvoice(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction addCartItem(Long transactionId, Long itemId, int quantity){
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);

        if(transaction == null){
            return null;
        }

        Item item = itemRepository.findById(itemId).orElse(null);

        if(item == null){
            return null;
        }

        Stock stock = stockService.getStockById(item.getId());

        if(stock == null){
            return null;
        }

        transaction.getSaleItems().add(item);

        transaction.setTotalPrice(transaction.getTotalPrice() + item.getPrice() * quantity);

        int newQuantity = stock.getQuantity() - quantity;

        if(newQuantity < 0){
            return null;
        }
        stock.setQuantity(newQuantity);
        stockService.updateStock(stock.getId(), stock);

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction removeCartItem(Long transactionId, Long itemId, int quantity){
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);

        if(transaction == null){
            return null;
        }

        Item item = itemRepository.findById(itemId).orElse(null);

        if(item == null){
            return null;
        }

        Stock stock = stockService.getStockById(item.getId());


        transaction.getSaleItems().remove(item);

        transaction.setTotalPrice(transaction.getTotalPrice() - item.getPrice()*quantity);

        if (stock != null ) {
            stock.setQuantity(stock.getQuantity() + quantity); 
            stockService.updateStock(stock.getId(), stock);
        }
     
        return transactionRepository.save(transaction);
    }

    public Transaction updateInvoice(Long id, Transaction transaction){
        Transaction exitsTransaction = transactionRepository.findById(id).orElse(null);

        if(exitsTransaction == null){
            return null;
        }

        exitsTransaction.setStatus(transaction.getStatus());
        return transactionRepository.save(exitsTransaction);
    }

}
