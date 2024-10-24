package com.project.sprint1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HRDao {
    @Autowired
    JdbcTemplate jt;

    public void insertMessage(String sendid,
            String receiveid,
            String messageContent) {
        String sqlStmt = "insert into message";
        sqlStmt += "(sendid,receiveid,message_content) ";
        sqlStmt += "values(?,?,?)";
        jt.update(sqlStmt, sendid, receiveid, messageContent);
    }

    public List<Map<String, Object>> messageSelect(String id) {
        String sqlStmt = "select seq, sendid, receiveid, message_content as message, reg_dt, messagecheck ";
        sqlStmt += "from message where receiveid = ?";
        return jt.queryForList(sqlStmt, id);
    }

    // 0709 김혜림 코드 추가
    public List<Map<String, Object>> messageSendSelect(String id) {
        String sqlStmt = "select seq, sendid, receiveid, message_content as message, reg_dt ";
        sqlStmt += "from message where sendid = ?";
        return jt.queryForList(sqlStmt, id);
    }

    public Map<String, Object> userInfoSelect(String id) {
        String sqlStmt = "select ";
        sqlStmt += "seq, ";
        sqlStmt += "id, ";
        sqlStmt += "pw, ";
        sqlStmt += "name, ";
        sqlStmt += " phone, ";
        sqlStmt += "email ";
        sqlStmt += "from user ";
        sqlStmt += "where id = ?";
        return jt.queryForMap(sqlStmt, id);
    }

    public List<Map<String, Object>> userBoardSelect(String id) {
        String sqlStmt = "select ";
        sqlStmt += "seq, ";
        sqlStmt += "code, ";
        sqlStmt += "title, ";
        sqlStmt += "reg_dt as regDt, find ";
        sqlStmt += "from post ";
        sqlStmt += "where id = ?";
        return jt.queryForList(sqlStmt, id);
    }

    public void deleteUser(String id) {
        String sqlStmt = "delete from user where id = ?";
        jt.update(sqlStmt, id);
    }

    public void updatePhone(String userId, String phone) {
        String sqlStmt = "update user set phone = ? where id = '" + userId + "'";
        jt.update(sqlStmt, phone);
    }

    public void updateEmail(String userId, String email) {
        String sqlStmt = "update user set email = ? where id = '" + userId + "'";
        jt.update(sqlStmt, email);
    }

    public void updatePw(String userId, String pw) {
        String sqlStmt = "update user set pw = ? where id = '" + userId + "'";
        jt.update(sqlStmt, pw);
    }

    // 0709 김혜림 코드 추가
    public void messageDelete(String seq) {
        String sqlStmt = "delete from message where seq = ?";
        jt.update(sqlStmt, seq);
    }

    // 비밀번호 찾기 기능
    public String findPasswordSelect(String id) {
        String sqlStmt = "select pw from user where id = ?";
        List<String> results = jt.query(sqlStmt, new Object[] { id }, (rs, rowNum) -> rs.getString("pw"));
        return results.isEmpty() ? null : results.get(0);
    }

    // 쪽지 확인하면 확인했다고 뜨는 거아ㅣㅁ
    public void messageCheckUpdate(String seq) {
        String sqlStmt = "update message set messagecheck = 1 where seq = ?";
        jt.update(sqlStmt, seq);
    }

    // 찾고있는지 찾았는지 확인.
    public void findUpdate(String find, String seq) {
        String sqlStmt = "update post set find = ? where seq = ?";
        jt.update(sqlStmt, find, seq);
    }
}
