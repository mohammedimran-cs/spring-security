package com.imran.security_service.service;

import com.imran.security_service.entity.UserPrincipal;
import com.imran.security_service.entity.Users;
import com.imran.security_service.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByName(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user.getName(),user.getPassword(),user.getRole());
    }
}
