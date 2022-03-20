package com.example.hellowebsite.member.service;

import com.example.hellowebsite.member.entity.Member;
import com.example.hellowebsite.member.model.MemberInput;
import com.example.hellowebsite.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    public boolean register(MemberInput parameter) {
        Optional<Member> optionalMember =  memberRepository.findById(parameter.getUserId());
        if(optionalMember.isPresent()){
            return false;
        }

        Member member = new Member();
        member.setUserId(parameter.getUserId());
        member.setUserName(parameter.getUserName());
        member.setPhone(parameter.getPhone());
        member.setPassword(parameter.getPassword());
        member.setRegDt(LocalDateTime.now());
        memberRepository.save(member);

        return true;
    }


}
