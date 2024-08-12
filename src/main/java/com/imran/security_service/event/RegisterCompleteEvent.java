package com.imran.security_service.event;

import com.imran.security_service.entity.Users;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class RegisterCompleteEvent extends ApplicationEvent {
    private Users user;
    private String applicationUrl;
    public RegisterCompleteEvent(Users user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
