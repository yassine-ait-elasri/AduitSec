package io.audit.audit_sec.repos;

import io.audit.audit_sec.domain.Users;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UsersRepository extends MongoRepository<Users, Long> {
}
