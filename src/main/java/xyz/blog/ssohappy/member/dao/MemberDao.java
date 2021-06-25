package xyz.blog.ssohappy.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import xyz.blog.ssohappy.member.Member;

@Repository
public class MemberDao implements IMemberDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberDao(ComboPooledDataSource comboPooledDataSource) {
        this.jdbcTemplate = new JdbcTemplate(comboPooledDataSource);
    }

    @Override
    public List<Member> findAllMembers() {
        final String selectMember_sql =
                "SELECT * FROM member";

        /*jdbctemplate + BeanProepertyRowMapper를 활용하여 Member의 내용을 전부 가져옴
         * 대신에 Category 클래스를 생성해 놓아야.*/
        return jdbcTemplate.query(selectMember_sql,
                new BeanPropertyRowMapper<>(Member.class));
    }

    @Override
    public Member selectMember(Member member) {
        List<Member> memberList = null;

        final String selectMember_sql = "SELECT * FROM member WHERE memberID = ? AND memberPW = ?";

        memberList = jdbcTemplate.query(selectMember_sql, new RowMapper<Member>() {
            public Member mapRow(ResultSet resultSet, int i) throws SQLException {
                Member member_dbData = new Member();

                member_dbData.setMemberID(resultSet.getString("memberID"));
                member_dbData.setMemberPW(resultSet.getString("memberPW"));

                return member_dbData;
            }
        }, member.getMemberID(), member.getMemberPW());

        if (memberList.isEmpty()) {
            return null;
        }

        return memberList.get(0);
    }

    @Override
    public int insertMember(Member member) {
        int resultInsertMember = 0;

        final String insertMember_sql = "INSERT INTO member (memberID, memberPW, memberINFO) VALUES (?, ?, ?)";

        resultInsertMember = jdbcTemplate.update(insertMember_sql,
                member.getMemberID(), member.getMemberPW(), member.getMemberINFO());

        return resultInsertMember;
    }
}
