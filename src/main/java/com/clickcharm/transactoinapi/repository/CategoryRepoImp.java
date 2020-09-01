package com.clickcharm.transactoinapi.repository;

import com.clickcharm.transactoinapi.exception.ExpTrackBadRequestException;
import com.clickcharm.transactoinapi.exception.ExpTrackNotFoundException;
import com.clickcharm.transactoinapi.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

@Repository
public class CategoryRepoImp implements CategoryRepository {

  @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_BY_ID_QUERY = "SELECT C.CATEGORY_ID, C.USER_ID, C.TITLE, C.DESCRIPTION," +
            "            COALESCE(SUM(T.AMOUNT), 0) TOTAL_EXPENSE FROM ET_TRANSACTIONS T RIGHT OUTER JOIN " +
            "            ET_CATEGORY C ON C.CATEGORY_ID = T.CATEGORY_ID WHERE C.USER_ID =?" +
            "            AND C.CATEGORY_ID = ? GROUP BY C.CATEGORY_ID";

    private static final String CREATE_CATE_QUERY = "INSERT INTO et_category (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION) VALUES (NEXTVAL('et_category_seq'),?,?,?)";

    private static final String ALL_CATEGORY_QUERY = "SELECT C.CATEGORY_ID, C.USER_ID, C.TITLE, C.DESCRIPTION," +
            "   COALESCE(SUM(T.AMOUNT), 0) TOTAL_EXPENSE FROM ET_TRANSACTIONS T RIGHT OUTER JOIN " +
            "    ET_CATEGORY C ON C.CATEGORY_ID = T.CATEGORY_ID WHERE C.USER_ID =?" +
            "  GROUP BY C.CATEGORY_ID ";
    private static final String UPDATE_CATEGORY_QUERY = "UPDATE et_category SET TITLE = ? , DESCRIPTION = ? WHERE USER_ID= ? AND CATEGORY_ID = ?";


    @Override
    public List<Category> findAll(int userId) throws ExpTrackNotFoundException {
        return jdbcTemplate.query(ALL_CATEGORY_QUERY,new Object[]{userId}, categoryRowMapper);
    }

    @Override
    public Category findById(int userId, int categoryId) throws ExpTrackNotFoundException {
       try{
            return  jdbcTemplate.queryForObject(GET_BY_ID_QUERY, new Object[]{userId,categoryId},categoryRowMapper);
       }catch (Exception e){
           throw new ExpTrackNotFoundException("Category Not Found " + e.toString());
       }
    }

    @Override
    public Integer create(Category category) throws ExpTrackBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CATE_QUERY, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1,category.getUserId());
                preparedStatement.setString(2,category.getTitle());
                preparedStatement.setString(3,category.getDescription());
                return preparedStatement;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("CATEGORY_ID");
        } catch (Exception e) {
            //System.out.println(Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n"));
            throw new ExpTrackBadRequestException("Invalid Request : " + e.toString());
        }
    }

    @Override
    public void update(int userId, int categoryId, Category newCategory) throws ExpTrackBadRequestException {
        try {
           // System.out.println("userId : " + userId +"\ncategoryId : " + categoryId + "\ntitle : " + newCategory.getTitle() +"\ndescription : "+newCategory.getDescription());
            jdbcTemplate.update(UPDATE_CATEGORY_QUERY, new Object[]{newCategory.getTitle(), newCategory.getDescription(), userId, categoryId});
        }catch (Exception e){
            throw new ExpTrackBadRequestException("Invalid Request : "+e.toString());
        }
    }

    @Override
    public void removeById(int userId, int categoryId) throws ExpTrackBadRequestException {

    }

    private RowMapper<Category> categoryRowMapper = ((rs, rowNum) -> {
        return new Category(rs.getInt("CATEGORY_ID"),
                rs.getInt("USER_ID"),
                rs.getString("TITLE"),
                rs.getString("DESCRIPTION"),
                rs.getDouble("TOTAL_EXPENSE"));
    });
}
