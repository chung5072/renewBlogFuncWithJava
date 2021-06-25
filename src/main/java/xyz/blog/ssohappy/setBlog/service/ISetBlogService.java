package xyz.blog.ssohappy.setBlog.service;

import java.util.List;

import xyz.blog.ssohappy.setBlog.Background;

public interface ISetBlogService {
    List<Background> findAllBackgroundImages();
    void setAsBackgroundImage(int backgroundID);
    void clearBackgroundImage(String backgroundSet);
}
