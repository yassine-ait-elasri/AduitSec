package io.audit.audit_sec.service;
import io.audit.audit_sec.repos.SecurityAuditRepository;
import io.audit.audit_sec.domain.SecurityAudit;
// SecurityAuditService.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityAuditService {

    private final SecurityAuditRepository auditRepository;

    @Autowired
    public SecurityAuditService(SecurityAuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public List<SecurityAudit> getAllAudits() {
        return auditRepository.findAll();
    }

    public SecurityAudit createAudit(SecurityAudit audit) {
        return auditRepository.save(audit);
    }

    // Add other service methods as needed
}
