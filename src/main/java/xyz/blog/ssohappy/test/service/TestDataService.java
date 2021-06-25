package xyz.blog.ssohappy.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.blog.ssohappy.test.TestData;
import xyz.blog.ssohappy.test.dao.TestDataDao;

@Service
public class TestDataService implements ITestDataService {
    @Autowired
    TestDataDao testDataDao;

    @Override
    public List<TestData> findAll() {
        TestData testData = new TestData();
        List<TestData> testDatas = testDataDao.findAll();

        if (testDatas.isEmpty()) {
            testData.setIndex(0);
            testData.setValue("no data");
            testDatas.add(0, testData);
        } 
        
        return testDatas;
    }

    @Override
    public void registerTestData(TestData testData) {
        int resultRegister = testDataDao.insertTestData(testData);

        if (resultRegister == 0){
            System.out.println("등록 실패!");
        }
        else {
            System.out.println("등록 성공!");
        }
        
    }
    
}
