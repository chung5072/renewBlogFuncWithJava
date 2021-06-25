package xyz.blog.ssohappy.test.dao;

import java.util.List;

import xyz.blog.ssohappy.test.TestData;

public interface ITestDataDao {
    List<TestData> findAll();
    int insertTestData(TestData testData);
}
