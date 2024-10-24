package com.project.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
    @Autowired
    JdbcTemplate jt;

    public List<Map<String, Object>> location(String dong) {
        String sqlStmt = "select distinct ";
        sqlStmt += "sido,";
        sqlStmt += "gu,";
        sqlStmt += "dong, ";
        sqlStmt += "ri, ";
        sqlStmt += "lat, ";
        sqlStmt += "lon ";
        sqlStmt += "from location ";
        sqlStmt += "where dong like ?";
        return jt.queryForList(sqlStmt, dong);
    }

    public List<Map<String, Object>> locationDetail() {
        String sqlStmt = "select dong_detail as dongDetail from location";
        return jt.queryForList(sqlStmt);
    }

    public void boardInsert(String id,
            String code,
            String title,
            String date,
            String location1,
            String location2,
            String lon,
            String lat,
            String filePath,
            String category,
            String itemName,
            String content) {
        String sqlStmt = "INSERT INTO post(id, code, title, item_date, location1, location2, lon, lat, ";
        sqlStmt += "item_image, item_category, item_name, content) ";
        sqlStmt += "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        jt.update(sqlStmt, id, code, title, date, location1, location2, lon, lat, filePath, category, itemName,
                content);
    }

    public List<Map<String,Object>> category(){
        String sqlStmt = "select category_code as catCode, category_name as catName ";
               sqlStmt += "from category";
        return jt.queryForList(sqlStmt);
    }
    public List<Map<String,Object>> categoryDetail(String catCode){
        String sqlStmt = "select category_code as catCode, ";
               sqlStmt += "category_detail_code as catDeCode, ";
               sqlStmt += "category_detail as catDeName ";
               sqlStmt += "from category_detail ";
               sqlStmt += "where category_code = ?";
        return jt.queryForList(sqlStmt,catCode);
    }

    public void cntIncrease(int seq){
        String sqlStmt = "update post set cnt = cnt+1 where seq = ?";
        jt.update(sqlStmt, seq);
    }

}
