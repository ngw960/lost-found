package com.project.sprint1.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PostDao {
    @Autowired
    JdbcTemplate jt;
    // 게시글 목록 조회
    // public List<Map<String, Object>> boardSelect() {
    //     String sqlStmt = "SELECT code, id, item_category as itemCategory, item_name as itemName,";
    //                 sqlStmt += "item_date as itemDate, item_image as itemImage, location1, location2,";
    //                 sqlStmt += "title, content, reg_dt as regDt FROM  post";
    //     return jt.queryForList(sqlStmt);
    // } // MainDao 에 있는 기능과 합쳐질 수 있음

    // 게시글 상세 조회
    public Map<String, Object> postDetail(int seq) {
        String sqlStmt = "SELECT seq,code, id, item_category as itemCategory, item_name as itemName,";
                    sqlStmt += " item_date as itemDate, item_image as itemImage, location1, location2,";
                    sqlStmt += " title, content, reg_dt as regDt FROM post WHERE seq = ?";
        return jt.queryForMap(sqlStmt, seq);
    }

    public List<Map<String, Object>> commentSelect(int seq) {
        String sqlStmt = "SELECT seq,id, comment as commentContent, reg_dt as regDt ";
                    sqlStmt += "FROM comment WHERE board_seq = ?";
        return jt.queryForList(sqlStmt, seq);
    }

    // 2024-07-08
    // 게시글 수정
    public void updatePost(int seq, String title, String content) {
        String sqlStmt = "UPDATE post SET title = ?, content = ? WHERE seq = ?";
        jt.update(sqlStmt, title, content, seq);
    }

    // 2024-07-08
    // 게시글 삭제
    public void deletePost(int seq) {
        String sqlStmt = "DELETE FROM post WHERE seq = ?";
        jt.update(sqlStmt, seq);
    }

    // 댓글 입력
    public void insertComment(String postSeq, String userId, String content) {
        String sqlStmt = "INSERT INTO comment (board_seq, id, comment) VALUES (?, ?, ?)";
        jt.update(sqlStmt, postSeq, userId, content);
    }

    // 댓글 삭제
    public void deleteComment(String seq) {
        String sqlStmt = "DELETE FROM comment WHERE seq = ?";
        jt.update(sqlStmt, seq);
    }

    // 댓글 수정
    public void updateComment(String newContent, String commentseq) {
    String sqlStmt = "update comment set comment = ? where seq = ?";
    jt.update(sqlStmt, newContent,commentseq);
    }

    
}