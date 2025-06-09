package com.mcp.controller;

import com.mcp.model.CasaTransaction;
import com.mcp.repository.CasaTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CasaTransactionController {
    @Autowired
    private CasaTransactionRepository casaTransactionRepository;

    @GetMapping("/casa-transactions")
    public List<CasaTransaction> getAllCasaTransactions() {
        return casaTransactionRepository.findAll();
    }
} 