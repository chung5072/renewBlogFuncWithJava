package xyz.blog.ssohappy.setBlog.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
// import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import xyz.blog.ssohappy.setBlog.Background;
import xyz.blog.ssohappy.setBlog.dao.SetBlogDao;
import xyz.blog.ssohappy.setBlog.service.SetBlogService;

@Controller
@RequestMapping("/setBlog")
public class SetBlogController {
    @Autowired
    SetBlogDao setBlogDao;
    @Autowired
    SetBlogService setBlogService;

    @RequestMapping(value = "/uploadBackgroundImagePage", method = RequestMethod.GET)
    public String uploadBackgroundImagePage() {
        return "/setBlog/uploadBackgroundImagePage";
    }

    @RequestMapping(value = "/uploadBackgroundImage", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
    public String uploadBackgroundImage(@RequestParam("backgroundID") int backgroundID,
                                       @RequestParam("backgroundImage") MultipartFile backgroundImage)
            throws IOException {
        int resultInsert = setBlogDao.insertBackgroundImage(backgroundID, backgroundImage);

        if(resultInsert == 0) {
            System.out.println("Background insert failed!");
        }
        else {
            System.out.println("Background insert success!");
        }

        return "redirect:/index";
    }

    @RequestMapping(value = "/setBackgroundImagePage", method = RequestMethod.GET)
    public String setBackgroundImagePage(Model model) throws UnsupportedEncodingException {
        List<Background> backgrounds = setBlogDao.findAllBackgroundImages();
        Map<Integer, String> backgroundImages = new HashMap<>();

        for (Background background : backgrounds) {
            int backgroundIDs = background.getBackgroundID();
            byte[] encode = Base64.getEncoder().encode(background.getBackgroundImage());
            String backgroundEncodes = new String(encode, "UTF-8");
            backgroundImages.put(backgroundIDs, backgroundEncodes);
        }

        model.addAttribute("backgroundImages", backgroundImages);
        return "/setBlog/setBackgroundImagePage";
    }

    @RequestMapping(value = "/getSelectedImageSrc", method = RequestMethod.POST)
    public String getSelectedImageSrc(@RequestParam("inputImageSrc") String imageLabel) {
        int selectedImageID = Integer.parseInt(imageLabel);
        /*일단 기존의 테이블 정보를 받아서 t/f를 확인
        * 새로 받은 숫자의 t/f를 확인
        * 다를 경우에 기존의 t를 f로, default의 f를 t로 변경
        * 변경한 t의 인덱스 값을 전송?
        * 그리고 t의 인덱스 값의 html로 받아서 배경화면으로 변경*/

        List<Background> backgrounds = setBlogDao.findAllBackgroundImages();

        for (Background background : backgrounds) {
            if (background.getBackgroundSet() == "T") {
                if (background.getBackgroundID() == selectedImageID) {
                    break;
                }
            } else {
                setBlogService.clearBackgroundImage("T");
                setBlogService.setAsBackgroundImage(selectedImageID);
                break;
            }
        }
        return "redirect:/index";
    }
}
