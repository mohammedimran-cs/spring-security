package com.imran.security_service.repository;


import com.imran.security_service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    Users findByName(String name);
}
