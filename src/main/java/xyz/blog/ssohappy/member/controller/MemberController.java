package xyz.blog.ssohappy.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.blog.ssohappy.member.Member;
import xyz.blog.ssohappy.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/loginMemberPage", method = RequestMethod.GET) 
    public String loginMemberPage() {
        
        return "/member/loginMemberPage";
    }

    @RequestMapping(value = "/loginMember", method = RequestMethod.POST)
    public String loginMember(Member member, HttpSession httpSession) {
        Member loginedMember = memberService.searchMember(member);

        if (loginedMember == null) {
            System.out.println("가입되지 않은 유저입니다.");
        }

        httpSession.setAttribute("loginedMember", loginedMember);
        
        return "redirect:/index";
    }

    @RequestMapping(value = "/joinMember", method = RequestMethod.POST)
    public String joinMember(Member member) {
        memberService.registerMember(member);
        
        return "redirect:/member/loginMemberPage";
    }

    @RequestMapping(value = "/logoutMember", method = RequestMethod.GET)
    public String logoutMember(Member member, HttpSession httpSession) {
        httpSession.invalidate();

        return "redirect:/index";
    }
}
