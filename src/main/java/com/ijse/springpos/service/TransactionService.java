package com.ijse.springpos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.springpos.entity.Transaction;

@Service
public interface TransactionService {
    List<Transaction> getALLTransactions();
    Transaction getTransactionById(Long id);
    Transaction createInvoice(Transaction transaction);
    Transaction updateInvoice(Long id, Transaction transaction);
    Transaction addCartItem(Long transactionId, Long itemId, int quantity);
    Transaction removeCartItem(Long transactionId, Long itemId, int quantity);
  
}
