package com.mcp.controller;

import com.mcp.model.CustomerRelationship;
import com.mcp.repository.CustomerRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRelationshipController {
    @Autowired
    private CustomerRelationshipRepository customerRelationshipRepository;

    @GetMapping("/customer-relationships")
    public List<CustomerRelationship> getAllCustomerRelationships() {
        return customerRelationshipRepository.findAll();
    }
} 