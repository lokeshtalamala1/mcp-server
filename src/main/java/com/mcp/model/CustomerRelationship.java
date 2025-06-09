package com.mcp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "customer_relationships")
public class CustomerRelationship {
    @Id
    @Column(name = "relationship_id")
    private String relationshipId;

    @Column(name = "primary_cust_id")
    private String primaryCustId;

    @Column(name = "related_cust_id")
    private String relatedCustId;

    @Column(name = "relationship_type")
    private String relationshipType;

    @Column(name = "joint_account_id")
    private String jointAccountId;

    @Column(name = "access_level")
    private String accessLevel;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "status")
    private String status;
}
