package xyz.blog.ssohappy.test.dao;

import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import xyz.blog.ssohappy.test.TestData;

@Repository
public class TestDataDao implements ITestDataDao{

    private JdbcTemplate jdbcTemplate;

    public TestDataDao(ComboPooledDataSource comboPooledDataSource) {
        this.jdbcTemplate = new JdbcTemplate(comboPooledDataSource);
    }
    

    @Override
    public List<TestData> findAll() {
        /*테스트 데이터의 전체 내용을 가져옴*/
        final String testDataSelect_sql =
                "SELECT * FROM test";
                
        return jdbcTemplate.query(testDataSelect_sql, new BeanPropertyRowMapper<>(TestData.class));
    }


    @Override
    public int insertTestData(TestData testData) {
        int resultInsert = 0;

        final String insertTestData_sql =
                "INSERT INTO test (index, value) VALUES (?, ?)";

        /*JdbcTemplate 활용 > 첫 번째 ?에 memberId, 두 번째 ?에 memberPw 정보가 들어감*/
        resultInsert = jdbcTemplate.update(
            insertTestData_sql, testData.getIndex(), testData.getValue());

        return resultInsert;
    }
    
}
