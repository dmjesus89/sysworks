package com.armstech.sysjob.mvc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.armstech.sysjob.mvc.model.User;
import com.armstech.sysjob.mvc.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserSecurityService implements UserDetailsService {



    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepository.findByMail(mail);
        if (null == user) {
            log.warn("Mail {} not found", mail);
            throw new UsernameNotFoundException("Mail " + mail + " not found");
        }
        return user;
    }
}
