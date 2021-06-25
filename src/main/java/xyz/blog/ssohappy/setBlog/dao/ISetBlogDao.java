package xyz.blog.ssohappy.setBlog.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import xyz.blog.ssohappy.setBlog.Background;

public interface ISetBlogDao {
    List<Background> findAllBackgroundImages();
    int insertBackgroundImage(int backgroundID, MultipartFile backgroundImage) throws IOException;
    int setAsBackgroundImage(int backgroundID);
    int clearBackgroundImage(String backgroundSet);
}
