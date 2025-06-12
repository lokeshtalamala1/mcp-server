package com.mcp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_relationships")
@Data
public class CustomerRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "relationship_type")
    private String relationshipType;

    @Column(name = "related_customer_id")
    private String relatedCustomerId;

    @Column(name = "relationship_status")
    private String relationshipStatus;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
} 