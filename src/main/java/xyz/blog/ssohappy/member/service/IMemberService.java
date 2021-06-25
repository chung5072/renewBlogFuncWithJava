package xyz.blog.ssohappy.member.service;

import java.util.List;

import xyz.blog.ssohappy.member.Member;

public interface IMemberService {
    List<Member> findAllMembers();
    Member searchMember(Member member);
    void registerMember(Member member);
}
