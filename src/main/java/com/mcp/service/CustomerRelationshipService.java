package com.mcp.service;

import com.mcp.entity.CustomerRelationship;
import com.mcp.repository.CustomerRelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerRelationshipService {
    private final CustomerRelationshipRepository relationshipRepository;

    @Transactional(readOnly = true)
    public List<CustomerRelationship> getRelationshipsByCustomerId(String customerId) {
        return relationshipRepository.findByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public List<CustomerRelationship> getActiveRelationshipsByCustomerId(String customerId) {
        return relationshipRepository.findActiveRelationshipsByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public List<CustomerRelationship> getRelationshipsByType(String customerId, String type) {
        return relationshipRepository.findByCustomerIdAndType(customerId, type);
    }

    @Transactional(readOnly = true)
    public List<CustomerRelationship> getActiveRelationshipsAtDate(LocalDateTime date) {
        return relationshipRepository.findActiveRelationshipsAtDate(date);
    }

    @Transactional
    public CustomerRelationship createRelationship(CustomerRelationship relationship) {
        relationship.setStartDate(LocalDateTime.now());
        relationship.setRelationshipStatus("ACTIVE");
        return relationshipRepository.save(relationship);
    }

    @Transactional
    public CustomerRelationship updateRelationship(CustomerRelationship relationship) {
        return relationshipRepository.save(relationship);
    }

    @Transactional
    public void endRelationship(Long relationshipId) {
        relationshipRepository.findById(relationshipId).ifPresent(relationship -> {
            relationship.setEndDate(LocalDateTime.now());
            relationship.setRelationshipStatus("INACTIVE");
            relationshipRepository.save(relationship);
        });
    }

    @Transactional(readOnly = true)
    public List<CustomerRelationship> getRelatedCustomers(String customerId) {
        return relationshipRepository.findByRelatedCustomerId(customerId);
    }
} 