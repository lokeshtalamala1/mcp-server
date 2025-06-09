package com.mcp.controller;

import com.mcp.model.CardTransaction;
import com.mcp.repository.CardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CardTransactionController {
    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @GetMapping("/card-transactions")
    public List<CardTransaction> getAllCardTransactions() {
        return cardTransactionRepository.findAll();
    }
} 