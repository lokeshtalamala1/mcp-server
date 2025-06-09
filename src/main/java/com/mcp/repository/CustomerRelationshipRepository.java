package com.mcp.repository;

import com.mcp.model.CustomerRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRelationshipRepository extends JpaRepository<CustomerRelationship, String> {
} 