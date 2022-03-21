package com.example.hellowebsite.member.service;

import com.example.hellowebsite.admin.dto.MemberDto;
import com.example.hellowebsite.admin.model.MemberParam;
import com.example.hellowebsite.member.entity.Member;
import com.example.hellowebsite.member.model.MemberInput;
import com.example.hellowebsite.member.model.ResetPasswordRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


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
     *
     * @param uuid
     * @return Check if the input uuid value is valid.
     */
    boolean checkResetPassword(String uuid);

    /**
     *
     * @return Member list (only in admin)
     */
    List<MemberDto> list(MemberParam parameter);

    /**
     *
     * @param userId
     * @return member detail
     */
    MemberDto detail(String userId);

    boolean updateStatus(String userId, String userStatus);

    boolean updatePassword(String userId, String password);
}
