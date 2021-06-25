package xyz.blog.ssohappy.setBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.blog.ssohappy.setBlog.Background;
import xyz.blog.ssohappy.setBlog.dao.SetBlogDao;

@Service
public class SetBlogService implements ISetBlogService {

    @Autowired
    SetBlogDao setBlogDao;

    @Override
    public List<Background> findAllBackgroundImages() {
        List<Background> backgroundImages = setBlogDao.findAllBackgroundImages();
        if (backgroundImages.isEmpty()) {
            System.out.println("이미지 없습니다.");
        } else {
            System.out.println("배경 이미지를 호출합니다.");
        }
        return backgroundImages;
    }

    @Override
    public void setAsBackgroundImage(int backgroundID) {
        int resultSetBackground = setBlogDao.setAsBackgroundImage(backgroundID);

        if (resultSetBackground == 0) {
            System.out.println("배경 이미지 설정에 실패했어요!");
        } else {
            System.out.println("배경 이미지 설정에 성공했어요!");
        }
    }

    @Override
    public void clearBackgroundImage(String backgroundSet) {
        int resultClearBackground = setBlogDao.clearBackgroundImage(backgroundSet);

        if (resultClearBackground == 0) {
            System.out.println("배경 이미지 해제에 실패했어요!");
        } else {
            System.out.println("배경 이미지 해제에 성공했어요!");
        }
        
    }

    
    
}
