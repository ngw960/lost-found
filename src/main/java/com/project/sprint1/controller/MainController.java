package com.project.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.sprint1.dao.MainDao;
import com.project.sprint1.dao.PostDao;

import java.util.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    MainDao mainDao;
    @Autowired
    PostDao postDao;

    @GetMapping("main") // 메인 페이지
    public String main(HttpSession session, Model model) {
        // 세션에서 사용자 아이디 가져오기
        String userId = (String) session.getAttribute("userId");

        // userId가 admin인 경우를 처리하기 위해 모델에 추가
        model.addAttribute("userId", userId);

        List<Map<String, Object>> posts = mainDao.selectPost();
        // 게시글 목록을 모델에 추가
        model.addAttribute("posts", posts);

        return "main.html";
    }

    @GetMapping("/logout") // 로그아웃
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/main"; // 로그아웃 후 메인 페이지로 리다이렉트
    }

    @GetMapping("/search") // 메인 페이지 게시글 검색 (글 제목)
    public String searchPost(@RequestParam String keyword, Model model) {
        List<Map<String, Object>> searchResult = mainDao.selectSearchPost(keyword);
        model.addAttribute("posts", searchResult);
        model.addAttribute("keyword", keyword);
        return "search.html";
    }

    @GetMapping("/searchfi") // 주인을 찾아요!(습득물 페이지) 게시글 검색 (글 제목)
    public String searchFiPost(@RequestParam String keyword, Model model) {
        List<Map<String, Object>> searchResult = mainDao.selectSearchfiPost(keyword);
        model.addAttribute("posts", searchResult);
        model.addAttribute("keyword", keyword);
        return "menu/searchfi";
    }

    @GetMapping("/searchli") // 잃어버렸나요? (분실물 페이지) 게시글 검색 (글 제목)
    public String searchLiPost(@RequestParam String keyword, Model model) {
        List<Map<String, Object>> searchResult = mainDao.selectSearchLiPost(keyword);
        model.addAttribute("posts", searchResult);
        model.addAttribute("keyword", keyword);
        return "menu/searchli";
    }

    // 습득물
    @GetMapping("/finditem")
    public String finditem(Model model, HttpSession session) {
        if(session.getAttribute("id") != null){
                    List<Map<String, Object>> userposts = mainDao.selectUserFiPost();
        model.addAttribute("userfiposts", userposts);
        return "menu/finditem";
        }
        return "redirect:/main";
    }

    // 분실물
    @GetMapping("/lostitem")
    public String lostitem(Model model, HttpSession session) {
        if(session.getAttribute("id") != null){
            List<Map<String, Object>> userposts = mainDao.selectUserLiPost();
            model.addAttribute("userliposts", userposts);
            return "menu/lostitem";
        }
        return "redirect:/main";
    }

    // 내정보
    @GetMapping("/myinfo")
    public String myinfo() {
        return "menu/myinfo";
    }

    // 회원정보
    @GetMapping("/userinfo")
    public String userinfo() {
        return "menu/userinfo";
    }

}
