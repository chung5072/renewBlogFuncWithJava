package xyz.blog.ssohappy.member.dao;

import java.util.List;

import xyz.blog.ssohappy.member.Member;

public interface IMemberDao {
    List<Member> findAllMembers();
    Member selectMember(Member member);
    int insertMember(Member member);
}
