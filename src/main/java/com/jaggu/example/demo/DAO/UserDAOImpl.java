package com.jaggu.example.demo.DAO;

import com.jaggu.example.demo.POJO.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void insertUser(Users usr) {
        String sql = "INSERT INTO users " +
                "(name, phone, address) VALUES (?, ?, ?)" ;
        assert getJdbcTemplate() != null;
        getJdbcTemplate().update(sql, usr.getName(), usr.getPhone(), usr.getAddress());
    }

    @Override
    public void insertUsers(List<Users> usr) {
        String sql = "INSERT INTO users " + "(name, phone, address) VALUES (?, ?, ?)";
        assert getJdbcTemplate() != null;
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Users user = usr.get(i);
                ps.setString(1, user.getName());
                ps.setString(2, user.getPhone());
                ps.setString(3, user.getAddress());
            }

            public int getBatchSize() {
                return usr.size();
            }
        });
    }

    @Override
    public List<Users> getAllUsers() {
        String sql = "SELECT * FROM users";
        assert getJdbcTemplate() != null;
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Users> result = new ArrayList<Users>();
        for(Map<String, Object> row:rows){
            Users usr = new Users();
            usr.setName((String)row.get("name"));
            usr.setPhone((String)row.get("phone"));
            usr.setAddress((String)row.get("address"));
            result.add(usr);
        }

        return result;
    }

    @Override
    public Users getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        assert getJdbcTemplate() != null;
        return (Users)getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Users>(){
            @Override
            public Users mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Users usr = new Users();
                usr.setName(rs.getString("name"));
                usr.setPhone(rs.getString("phone"));
                usr.setAddress(rs.getString("address"));
                return usr;
            }
        });
    }
}
