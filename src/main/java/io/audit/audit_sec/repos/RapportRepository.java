package io.audit.audit_sec.repos;

import io.audit.audit_sec.domain.Rapport;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RapportRepository extends MongoRepository<Rapport, Long> {
}
