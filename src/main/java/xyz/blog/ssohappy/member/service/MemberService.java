package xyz.blog.ssohappy.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.blog.ssohappy.member.Member;
import xyz.blog.ssohappy.member.dao.MemberDao;

@Service
public class MemberService implements IMemberService {
    @Autowired
    MemberDao memberDao;

    @Override
    public List<Member> findAllMembers() {
        List<Member> allMembers = memberDao.findAllMembers();
        
        return allMembers;
    }  

    @Override
    public Member searchMember(Member member) {
        Member resultMemberSearch = memberDao.selectMember(member);

        if (resultMemberSearch == null) {
            System.out.println("로그인에 실패했습니다");
        } else {
            System.out.println("로그인에 성공하셨습니다.");
            System.out.println(member.getMemberID() + "님, 환영합니다.");
        }

        return resultMemberSearch;
    }

    @Override
    public void registerMember(Member member) {
        int resultRegisterMember = memberDao.insertMember(member);

        if (resultRegisterMember == 0){
            System.out.println("회원 가입에 실패했습니다!");
        }
        else{
            System.out.println("회원 가입에 성공했습니다!");
        }
    }   
}
