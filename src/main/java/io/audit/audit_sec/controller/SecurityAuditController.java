package io.audit.audit_sec.controller;
import io.audit.audit_sec.service.SecurityAuditService;
import io.audit.audit_sec.repos.SecurityAuditRepository;
import io.audit.audit_sec.domain.SecurityAudit;
// SecurityAuditController.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
    @RequestMapping("/api/audits")
public class SecurityAuditController {

    private final SecurityAuditService auditService;

    @Autowired
    public SecurityAuditController(SecurityAuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    public ResponseEntity<List<SecurityAudit>> getAllAudits() {
        List<SecurityAudit> audits = auditService.getAllAudits();
        return ResponseEntity.ok(audits);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SecurityAudit> createAudit(@RequestBody SecurityAudit audit) {
        SecurityAudit createdAudit = auditService.createAudit(audit);
        System.out.println(createdAudit.getClient());
        return ResponseEntity.ok(createdAudit);
    }

    // Add other controller methods as needed
}
