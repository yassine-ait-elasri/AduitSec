package io.audit.audit_sec.repos;

// GenericEntityRepository.java
import org.springframework.data.mongodb.repository.MongoRepository;
import  io.audit.audit_sec.domain.GenericEntity;
public interface GenericEntityRepository extends MongoRepository<GenericEntity, String> {
}
