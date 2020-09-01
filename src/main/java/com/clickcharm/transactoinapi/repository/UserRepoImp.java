package com.clickcharm.transactoinapi.repository;

import com.clickcharm.transactoinapi.exception.ExpTrackException;
import com.clickcharm.transactoinapi.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;


@Repository
public class UserRepoImp implements UserRepository {
    private static final String CREATE_USER_QUERY = "INSERT INTO ET_USERS(USER_ID, FIRST_NAME,LAST_NAME, EMAIL,PASSWORD) VALUES (NEXTVAL('et_user_seq'),?,?,?,?)";
    private static final String COUNT_BY_EMAIL_QUERY = "SELECT COUNT(*) FROM ET_USERS WHERE EMAIL = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT USER_ID , FIRST_NAME, LAST_NAME, EMAIL, PASSWORD FROM ET_USERS WHERE USER_ID=?";
    private static final String LOGIN_USER_QUERY = "SELECT * FROM ET_USERS WHERE EMAIL=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(User user) {
        try {
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                ps.setString(4, hashedPassword);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        } catch (Exception e) {
            throw new ExpTrackException("Invalid detail, Failed to create user : " + e.toString());
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws ExpTrackException {
        try {
            User user = jdbcTemplate.queryForObject(LOGIN_USER_QUERY, new Object[]{email}, userRowMapper);
            if (!BCrypt.checkpw(password, user.getPassword()))
                throw  new ExpTrackException("Invalid Password");
            return user;
        } catch (Exception e) {
            throw new ExpTrackException("Invalid credentials : ");
        }
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(COUNT_BY_EMAIL_QUERY, new Object[]{email}, Integer.class);
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, new Object[]{userId}, userRowMapper);
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("USER_ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"));
    });
}
