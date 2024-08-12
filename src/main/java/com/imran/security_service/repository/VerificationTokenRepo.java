package com.imran.security_service.repository;

import com.imran.security_service.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken,Long> {

}
