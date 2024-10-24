package com.project.sprint1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GwDao {

    @Autowired
    JdbcTemplate jt;

    public boolean checkUserExists(String id, String email) {
        String sqlStmt = "SELECT COUNT(*) FROM user WHERE id = ? AND email = ?";
        Integer count = jt.queryForObject(sqlStmt, new Object[] { id, email }, Integer.class);
        return count != null && count > 0;
    }

    public void updatePassword(String id, String newPassword) {
        String sqlStmt = "UPDATE user SET pw = ? WHERE id = ?";
        jt.update(sqlStmt, newPassword, id);
    }
}
