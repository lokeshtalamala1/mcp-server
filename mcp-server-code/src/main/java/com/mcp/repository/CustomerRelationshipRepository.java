package com.mcp.repository;

import com.mcp.entity.CustomerRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRelationshipRepository extends JpaRepository<CustomerRelationship, Long> {
    List<CustomerRelationship> findByCustomerId(String customerId);
    
    List<CustomerRelationship> findByRelatedCustomerId(String relatedCustomerId);
    
    @Query("SELECT cr FROM CustomerRelationship cr WHERE cr.customerId = :customerId AND cr.relationshipStatus = 'ACTIVE'")
    List<CustomerRelationship> findActiveRelationshipsByCustomerId(@Param("customerId") String customerId);
    
    @Query("SELECT cr FROM CustomerRelationship cr WHERE cr.customerId = :customerId AND cr.relationshipType = :type")
    List<CustomerRelationship> findByCustomerIdAndType(@Param("customerId") String customerId, @Param("type") String type);
    
    @Query("SELECT cr FROM CustomerRelationship cr WHERE cr.startDate <= :date AND (cr.endDate IS NULL OR cr.endDate >= :date)")
    List<CustomerRelationship> findActiveRelationshipsAtDate(@Param("date") LocalDateTime date);
} 