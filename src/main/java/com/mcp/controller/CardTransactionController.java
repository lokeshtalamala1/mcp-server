package com.mcp.controller;

import com.mcp.model.CardTransaction;
import com.mcp.repository.CardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/card-transactions")
public class CardTransactionController {
    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @GetMapping
    public List<CardTransaction> getAllCardTransactions() {
        return cardTransactionRepository.findAll();
    }
} 