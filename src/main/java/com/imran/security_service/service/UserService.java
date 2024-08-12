package com.imran.security_service.service;

import com.imran.security_service.entity.Users;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
     void addUser(Users user, final HttpServletRequest request);
     List<Users> getALlUsers();

     void saveVerificationTokenForUser(Users user, String token);

    String verify(Users user);

    Users updateUser(Users user, Long userId);
}
