package com.example.hellowebsite.admin.mapper;

import com.example.hellowebsite.admin.dto.MemberDto;
import com.example.hellowebsite.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    long selectListCount(MemberParam parameter);
    List<MemberDto> selectList(MemberParam parameter);
}
