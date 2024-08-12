package com.imran.security_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class VerificationToken {
    private static final int EXPIRATION_TIME = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "userId",
            nullable = false,
            referencedColumnName = "id"
    )
    private Users user;

    public VerificationToken(Users user,String token){
        this.user = user;
        this.token = token;
        this.expirationTime = calculateExpirationData(EXPIRATION_TIME);
    }
    public VerificationToken(String token){
        this.token = token;
        this.expirationTime = calculateExpirationData(EXPIRATION_TIME);
    }

    public Date calculateExpirationData(int time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,time);
        return new Date(calendar.getTime().getTime());
    }
}
