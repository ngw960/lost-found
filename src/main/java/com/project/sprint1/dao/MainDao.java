package com.project.sprint1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MainDao {
    @Autowired
    JdbcTemplate jt;

    // 메인 메뉴에서 게시글 조회하기
    public List<Map<String, Object>> selectPost() {
        String sqlStmt = "SELECT seq,title,id, reg_dt as regDt, code, location1, item_image as itemImage, cnt,find FROM post";
        return jt.queryForList(sqlStmt);
    }

    // 습득물 게시판 게시물의 제목, 작성일자, code, 습득 상세 위치, 첨부이미지를 조희
    public List<Map<String, Object>> selectUserFiPost() {
        String sqlStmt = "SELECT seq,title,id, reg_dt as regDt, code, location1, ";
        sqlStmt += "item_image as itemImage, cnt,find FROM post where code = 1";

        return jt.queryForList(sqlStmt);
    }

    // 분실물 게시판 게시물의 제목, 작성일자, code, 습득 상세 위치, 첨부이미지를 조회
    public List<Map<String, Object>> selectUserLiPost() {
        String sqlStmt = "SELECT seq,title,id, reg_dt as regDt, code, location1, ";
        sqlStmt += "item_image as itemImage, cnt,find FROM post where code = 0";
        return jt.queryForList(sqlStmt);
    }

    // keyword(검색어)가 포함된 제목, 습득 상세 위치를 가진 게시글을 조회
    public List<Map<String, Object>> selectSearchPost(String keyword) {
        String sqlStmt = "SELECT seq, title,id, reg_dt as regDt, code, location1, ";
        sqlStmt += " item_image as itemImage, cnt,find FROM post WHERE title LIKE ? or content LIKE ?";
        return jt.queryForList(sqlStmt, "%" + keyword + "%", "%" + keyword + "%");
    }

    // 주인을 찾아요! (습득물) 게시판에서 keyword(검색어)가 포함된 제목, 습득 상세 위치를 가진 게시글을 조회
    public List<Map<String, Object>> selectSearchfiPost(String keyword) {
        String sqlStmt = "SELECT seq, title,id, reg_dt as regDt, code, location1, ";
        sqlStmt += " item_image as itemImage, cnt,find FROM post WHERE code = 1  and (title LIKE ? or content LIKE ?)";
        return jt.queryForList(sqlStmt, "%" + keyword + "%", "%" + keyword + "%");
    }

    // 잃어버렸나요? (분실물) 게시판에서 keyword(검색어)가 포함된 제목, 분실 상세 위치를 가진 게시글을 조회
    public List<Map<String, Object>> selectSearchLiPost(String keyword) {
        String sqlStmt = "SELECT seq, title,id, reg_dt as regDt, code, location1, ";
        sqlStmt += " item_image as itemImage, cnt,find FROM post WHERE code = 0  and (title LIKE ? or content LIKE ?)";
        return jt.queryForList(sqlStmt, "%" + keyword + "%", "%" + keyword + "%");
    }
}
