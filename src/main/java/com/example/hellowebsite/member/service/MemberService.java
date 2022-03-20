package com.example.hellowebsite.member.service;

import com.example.hellowebsite.member.model.MemberInput;
import com.example.hellowebsite.member.model.ResetPasswordRequest;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface MemberService extends UserDetailsService {
    boolean register(MemberInput parameter);

    /**
     * Activate the account corresponding to the uuid
     * @param uuid
     * @return
     */
    boolean emailAuth(String uuid);

    /**
     * Send password reset link to the email
     * @param parameter
     * @return
     */
    boolean sendResetPassword(ResetPasswordRequest parameter);

    /**
     * reset Password for the input uuid
     * @param id
     * @param password
     * @return
     */
    boolean resetPassword(String id, String password);

    /**
     * Check if the input uuid value is valid.
     * @param uuid
     * @return
     */
    boolean checkResetPassword(String uuid);
}
