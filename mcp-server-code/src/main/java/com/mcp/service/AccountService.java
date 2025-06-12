package com.mcp.service;

import com.mcp.entity.Account;
import com.mcp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Optional<Account> getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Transactional(readOnly = true)
    public List<Account> getAccountsByCustomerId(String customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public List<Account> getActiveAccountsByCustomerId(String customerId) {
        return accountRepository.findActiveAccountsByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public List<Account> getAccountsByType(String accountType) {
        return accountRepository.findByAccountType(accountType);
    }

    @Transactional(readOnly = true)
    public List<Account> getAccountsWithBalanceGreaterThan(Double minBalance) {
        return accountRepository.findAccountsWithBalanceGreaterThan(minBalance);
    }

    @Transactional
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Transactional
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Transactional
    public void deactivateAccount(String accountNumber) {
        accountRepository.findByAccountNumber(accountNumber)
            .ifPresent(account -> {
                account.setStatus("INACTIVE");
                accountRepository.save(account);
            });
    }
} 