package com.example.hellowebsite.admin.controller;

import com.example.hellowebsite.admin.dto.MemberDto;
import com.example.hellowebsite.admin.model.MemberParam;
import com.example.hellowebsite.member.entity.Member;
import com.example.hellowebsite.member.service.MemberService;
import com.example.hellowebsite.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminMemberController {
    private final MemberService memberService;

    @GetMapping("/admin/member/list.do")
    public String list(Model model, MemberParam parameter) {
        parameter.init();
        List<MemberDto> members = memberService.list(parameter);
        model.addAttribute("list", members);

        long totalCount = 0;
        if(members != null && members.size() > 0){
            totalCount = members.get(0).getTotalCount();
        }
        String queryString = "";

        PageUtil pageUtil = new PageUtil(totalCount, parameter.getPageIndex(), queryString);
        model.addAttribute("pager", pageUtil.pager());

        return "admin/member/list";
    }


}
