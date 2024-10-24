package com.project.sprint1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MessageDao {
    @Autowired
    JdbcTemplate jt;
    public List<Map<String,Object>> userSelect(String userId){
        String sqlStmt = "select seq,id,name ";
               sqlStmt += "from user ";
               
               // 0709 김혜림 코드추가 쪽지 보낼 회원에서 관리자는 뺐음 
               sqlStmt += "where id != ? and id != 'admin'";
        return jt.queryForList(sqlStmt, userId);
    }

    public int messageCnt(String userId){
        String sqlStmt = "select count(*) from message where receiveid = ? and messagecheck = 0";
        return jt.queryForObject(sqlStmt,int.class, userId);
    }
} 