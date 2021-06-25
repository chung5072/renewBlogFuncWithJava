package xyz.blog.ssohappy.test.service;

import java.util.List;

import xyz.blog.ssohappy.test.TestData;

public interface ITestDataService {
    List<TestData> findAll();
    void registerTestData(TestData testData);
}
