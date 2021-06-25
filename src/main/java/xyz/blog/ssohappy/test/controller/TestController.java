package xyz.blog.ssohappy.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.blog.ssohappy.test.TestData;
import xyz.blog.ssohappy.test.service.TestDataService;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestDataService testDataService;

    @RequestMapping(value = "/inputPage", method = RequestMethod.GET)
    public String inputPage() {
        return "/test/inputPage";
    }
    
    @RequestMapping(value = "/input", method = RequestMethod.POST)
    public String inputTest(TestData testData) {
        testDataService.registerTestData(testData);

        return "redirect:/index";
    }
}
