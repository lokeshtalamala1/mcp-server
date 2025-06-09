package com.mcp.controller;

import com.mcp.model.Account;
import com.mcp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
} 