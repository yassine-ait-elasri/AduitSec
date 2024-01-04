package io.audit.audit_sec.repos;
import io.audit.audit_sec.domain.SecurityAudit;
// SecurityAuditRepository.java

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecurityAuditRepository extends MongoRepository<SecurityAudit, String> {
}
