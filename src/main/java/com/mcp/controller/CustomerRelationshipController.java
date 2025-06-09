package com.mcp.controller;

import com.mcp.model.CustomerRelationship;
import com.mcp.repository.CustomerRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customer-relationships")
public class CustomerRelationshipController {
    @Autowired
    private CustomerRelationshipRepository customerRelationshipRepository;

    @GetMapping
    public List<CustomerRelationship> getAllCustomerRelationships() {
        return customerRelationshipRepository.findAll();
    }
} 