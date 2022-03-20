package com.example.hellowebsite.member.service;

import com.example.hellowebsite.member.model.MemberInput;
import com.example.hellowebsite.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface MemberService extends UserDetailsService {
    boolean register(MemberInput parameter);

    /**
     * Activate the account corresponding to the uuid
     * @param uuid
     * @return
     */
    boolean emailAuth(String uuid);


    boolean sendResetPassword(ResetPasswordInput parameter);
}
