package com.mcp.model;
import java.time.LocalDate;
import jakarta.persistence.*;

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

    public String getRelationshipId() { return relationshipId; }
    public void setRelationshipId(String relationshipId) { this.relationshipId = relationshipId; }
    public String getPrimaryCustId() { return primaryCustId; }
    public void setPrimaryCustId(String primaryCustId) { this.primaryCustId = primaryCustId; }
    public String getRelatedCustId() { return relatedCustId; }
    public void setRelatedCustId(String relatedCustId) { this.relatedCustId = relatedCustId; }
    public String getRelationshipType() { return relationshipType; }
    public void setRelationshipType(String relationshipType) { this.relationshipType = relationshipType; }
    public String getJointAccountId() { return jointAccountId; }
    public void setJointAccountId(String jointAccountId) { this.jointAccountId = jointAccountId; }
    public String getAccessLevel() { return accessLevel; }
    public void setAccessLevel(String accessLevel) { this.accessLevel = accessLevel; }
    public LocalDate getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(LocalDate effectiveDate) { this.effectiveDate = effectiveDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Getters and setters...
}
