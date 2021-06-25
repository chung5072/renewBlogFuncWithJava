package xyz.blog.ssohappy;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.blog.ssohappy.member.Member;
import xyz.blog.ssohappy.member.service.MemberService;
import xyz.blog.ssohappy.setBlog.Background;
import xyz.blog.ssohappy.setBlog.service.SetBlogService;
import xyz.blog.ssohappy.test.TestData;
import xyz.blog.ssohappy.test.service.TestDataService;

@Controller
public class HomeController {
    @Autowired
    TestDataService testDataService;
    @Autowired
    SetBlogService setBlogService;
    @Autowired
    MemberService memberService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET) 
    public String indexPage(Model model, HttpSession httpSession) throws UnsupportedEncodingException {
        // 로그인한 유저의 정보를 가져옴
        Member sessionMember = (Member) httpSession.getAttribute("loginedMember");
        // 모든 회원 정보를 가져옴
        // List<Member> allMembers = memberService.findAllMembers();
        
        // 테스트 데이터를 전부 불러옴
        List<TestData> testDatas = testDataService.findAll();
        
        // 배경화면으로 설정한 이미지를 불러옴
        List<Background> backgrounds = setBlogService.findAllBackgroundImages();
        List<String> backgroundImages = new ArrayList<>();

        for (Background background : backgrounds) {
            if (background.getBackgroundSet().equals("T")) {
                byte[] encode = Base64.getEncoder().encode(background.getBackgroundImage());
                String backgroundEncodes = new String(encode, "UTF-8");
                backgroundImages.add(backgroundEncodes);
            }
        }

        try {
            model.addAttribute("backgroundImage", backgroundImages.get(0));
        } catch (IndexOutOfBoundsException e) {
            model.addAttribute("backgroundImage", "none");
        }
        model.addAttribute("testDatas", testDatas);
        
        return "/index";
    }
    
}
