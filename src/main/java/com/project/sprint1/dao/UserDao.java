package com.project.sprint1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jt;

    public void signupAction(
            String userId,
            String userPw,
            String userName,
            String userPhone,
            String userEmail) {
        String sqlStmt = "INSERT INTO user(id, pw, name, phone, email) VALUES(?, ?, ?, ?, ?)";
        jt.update(sqlStmt, userId, userPw, userName, userPhone, userEmail);
    }

    public int dupIdCheck(String userId) { // ID 중복체크
        String sqlStmt = "SELECT COUNT(*) FROM user WHERE id = ?";
        return jt.queryForObject(sqlStmt, Integer.class, userId);
    }

    public Map<String, Object> login(String userId, String userPw) { // 회원가입 된 ID PW인지 확인
        String sqlStmt = "SELECT * FROM user id = ? AND pw = ?";
        return jt.queryForMap(sqlStmt, userId, userPw);
    }

    public void deleteUser(String userId) { // 삭제
        String sqlStmt = "DELETE FROM user WHERE id = ?";
        jt.update(sqlStmt, userId);
    }

    public List<Map<String, Object>> validatePassword(String userId, String userPw) { // 비밀번호 일치 확인
        String sqlStmt = "SELECT id, pw FROM user WHERE id = ? AND pw = ?";
        return jt.queryForList(sqlStmt, userId, userPw);
    }

    public int checkUser(String id, String pw) {
        String sqlStmt = "select count(*) from user where id = ? and pw = ?";
        return jt.queryForObject(sqlStmt, Integer.class, id, pw);
    }
}
