package com.mcp.controller;

import com.mcp.model.CasaTransaction;
import com.mcp.repository.CasaTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/casa-transactions")
public class CasaTransactionController {
    @Autowired
    private CasaTransactionRepository casaTransactionRepository;

    @GetMapping
    public List<CasaTransaction> getAllCasaTransactions() {
        return casaTransactionRepository.findAll();
    }
} 