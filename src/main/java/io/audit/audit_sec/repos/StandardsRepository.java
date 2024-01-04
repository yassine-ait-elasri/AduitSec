package io.audit.audit_sec.repos;

import io.audit.audit_sec.domain.Standards;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface StandardsRepository extends MongoRepository<Standards, Long> {
}
