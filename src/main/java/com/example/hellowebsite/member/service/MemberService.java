package com.example.hellowebsite.member.service;

import com.example.hellowebsite.member.model.MemberInput;


public interface MemberService {
    boolean register(MemberInput parameter);

    /**
     * Activate the account corresponding to the uuid
     * @param uuid
     * @return
     */
    boolean emailAuth(String uuid);


}
