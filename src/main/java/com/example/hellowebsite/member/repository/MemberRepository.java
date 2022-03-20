package com.example.hellowebsite.member.repository;

import com.example.hellowebsite.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {

}
