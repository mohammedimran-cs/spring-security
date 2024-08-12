package com.imran.security_service.service;

import com.imran.security_service.entity.Users;
import com.imran.security_service.entity.VerificationToken;
import com.imran.security_service.event.RegisterCompleteEvent;
import com.imran.security_service.repository.UserRepo;
import com.imran.security_service.repository.VerificationTokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VerificationTokenRepo verificationTokenRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;
    @Override
    public void addUser(Users user , final HttpServletRequest request) {
        userRepo.save(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        applicationEventPublisher.publishEvent(new RegisterCompleteEvent(
                user,applicationUrl(request)
        ));
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+
                request.getServerName()+
                ":"+
                request.getServerPort()+
                request.getContextPath();
    }

    @Override
    public List<Users> getALlUsers() {
        return userRepo.findAll();
    }

    @Override
    public void saveVerificationTokenForUser(Users user, String token) {
        VerificationToken verificationToken =
                new VerificationToken(user, token);
        verificationTokenRepo.save(verificationToken);
    }

    @Override
    public String verify(Users user) {
        Authentication authentication
                = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getName());
        }
        return "fail";
    }

    @Override
    public Users updateUser(Users user, Long userId) {
        Users db = userRepo.findById(userId).get();
        if(!user.getName().isEmpty()){
            db.setName(user.getName());
        }
        if (user.getRole() != null && !user.getRole().name().isEmpty()) {
            db.setRole(user.getRole());
        }
        return userRepo.save(db);
    }

}
