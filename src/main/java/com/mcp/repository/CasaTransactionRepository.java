package com.mcp.repository;

import com.mcp.model.CasaTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasaTransactionRepository extends JpaRepository<CasaTransaction, String> {
} 