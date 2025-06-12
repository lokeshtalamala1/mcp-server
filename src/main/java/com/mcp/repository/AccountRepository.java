package com.mcp.repository;

import com.mcp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    
    List<Account> findByCustomerId(String customerId);
    
    List<Account> findByAccountType(String accountType);
    
    @Query("SELECT a FROM Account a WHERE a.customerId = :customerId AND a.status = 'ACTIVE'")
    List<Account> findActiveAccountsByCustomerId(@Param("customerId") String customerId);
    
    @Query("SELECT a FROM Account a WHERE a.balance > :minBalance AND a.status = 'ACTIVE'")
    List<Account> findAccountsWithBalanceGreaterThan(@Param("minBalance") Double minBalance);
} 