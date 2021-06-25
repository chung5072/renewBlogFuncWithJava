package xyz.blog.ssohappy.setBlog.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import xyz.blog.ssohappy.setBlog.Background;

@Repository
public class SetBlogDao implements ISetBlogDao{

    private JdbcTemplate jdbcTemplate;

    public SetBlogDao(ComboPooledDataSource comboPooledDataSource) {
        this.jdbcTemplate = new JdbcTemplate(comboPooledDataSource);
    }

    @Override
    public List<Background> findAllBackgroundImages() {
        final String selectBackgrounds_sql =
                "SELECT * FROM background";

        return jdbcTemplate.query(selectBackgrounds_sql,
                new BeanPropertyRowMapper<>(Background.class));
    }

    @Override
    public int insertBackgroundImage(int backgroundID, MultipartFile backgroundImage) throws IOException {
        byte[] backgroundImageBytes = backgroundImage.getBytes();

        int resultInsert = 0;

        final String backgroundInsert_sql =
                "INSERT INTO background(backgroundID, backgroundImage) values (?, ?)";

        resultInsert = jdbcTemplate.update(backgroundInsert_sql,
                backgroundID, backgroundImageBytes);

        return resultInsert;
    }

    @Override
    public int setAsBackgroundImage(int backgroundID) {
        int resultUpdate = 0;

        final String sql = "UPDATE background " +
                "SET backgroundSet = ? " +
                "WHERE backgroundID = ?";

        resultUpdate = jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, "T");
                pstmt.setInt(2, backgroundID);
            }
        });
        return resultUpdate;
    }

    @Override
    public int clearBackgroundImage(String backgroundSet) {
        int resultUpdate = 0;

        final String sql = "UPDATE background " +
                "SET backgroundSet = ? " +
                "WHERE backgroundSet = ?";

        resultUpdate = jdbcTemplate.update(sql, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, "F");
                pstmt.setString(2, backgroundSet);
            }
        });
        return resultUpdate;
    }
    
}
