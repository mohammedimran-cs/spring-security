package com.imran.security_service.event.listener;

import com.imran.security_service.entity.Users;
import com.imran.security_service.event.RegisterCompleteEvent;
import com.imran.security_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
public class RegisterCompleteEventListener implements ApplicationListener<RegisterCompleteEvent> {
    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegisterCompleteEvent registerCompleteEventent) {
        Users user = registerCompleteEventent.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(user,token);
        String url = registerCompleteEventent.getApplicationUrl()
                +"verifyRegistration?token="+token;

        log.info("Click the link to verify the account: {}",url);
    }
}
