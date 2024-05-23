package com.ijse.springpos.controller;

import java.time.LocalDateTime;
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

import com.ijse.springpos.dto.TransactItemDto;
import com.ijse.springpos.entity.Transaction;
import com.ijse.springpos.service.TransactionService;

@RestController
@CrossOrigin(origins = "*")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public List<Transaction> getALLTransactions(){
        return transactionService.getALLTransactions();
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){

        Transaction transaction = transactionService.getTransactionById(id);

        if(transaction == null){
            return ResponseEntity.status(404).build();
        }else{
            return ResponseEntity.status(200).body(transaction);
        }
    }

    @PostMapping("/transactions")
    public Transaction createInvoice(){

        Transaction transaction = new Transaction();

        transaction.setTotalPrice(0.0);
        transaction.setOrderDate(LocalDateTime.now());
        transaction.setSaleItems(null);
        transaction.setStatus(0);
     
        return transactionService.createInvoice(transaction);
        
    }

    @PostMapping("/transactions/{id}/addCartItem")
    public Transaction addCartItem(@PathVariable Long id, @RequestBody TransactItemDto transactItemDto){

        return transactionService.addCartItem(id, transactItemDto.getItemId(), transactItemDto.getQuantity());
    }

    @DeleteMapping("/transactions/{tId}/removeCartItem")
    public Transaction removeCartItem(@PathVariable Long tId, @RequestBody TransactItemDto transactItemDto){
        return transactionService.removeCartItem(tId,transactItemDto.getItemId(),transactItemDto.getQuantity());
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<Transaction> updateInvoice(@PathVariable Long id, @RequestBody Transaction transaction){

        Transaction updateInvoices = transactionService.updateInvoice(id,transaction);

        if(updateInvoices == null) {
            return ResponseEntity.status(404).build();
        } else {
            return ResponseEntity.status(200).body(updateInvoices);
        }

    }
}
