package io.audit.audit_sec.domain;

// SecurityAudit.java

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class SecurityAudit {

    @Id
    private String id;

    private String projectName;
    private String projectDescription;
    private Date dateOfAudit;
    private String client;
    private String auditScope;
    private String technologiesUsed;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Date getDateOfAudit() {
        return dateOfAudit;
    }

    public void setDateOfAudit(Date dateOfAudit) {
        this.dateOfAudit = dateOfAudit;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAuditScope() {
        return auditScope;
    }

    public void setAuditScope(String auditScope) {
        this.auditScope = auditScope;
    }

    public String getTechnologiesUsed() {
        return technologiesUsed;
    }

    public void setTechnologiesUsed(String technologiesUsed) {
        this.technologiesUsed = technologiesUsed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
// Constructors, getters, setters
}
