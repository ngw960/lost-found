package com.project.sprint1.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.project.sprint1.dao.SRDao;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



// AdminController

@Controller
public class SRController {

    @Autowired
    SRDao srDao;

    // 관리자 페이지
    @GetMapping("admin")
    public String admin(HttpSession session, Model model) {
        int level = srDao.levelSelect(); 
        if(level == 999){
            List<Map<String,Object>> getAllUsers = srDao.getAllUsers();
            model.addAttribute("users", getAllUsers);            
            List<Map<String,Object>> getAllPosts = srDao.getAllPosts();

            model.addAttribute("posts", getAllPosts);
            return "adminpage";
        }
        else{
            return "redirect:/login";
        }
    }

    // 회원 삭제
    @PostMapping("/admin/deleteUser")
    public String deleteUser(@RequestParam String userId) {
        srDao.deleteUser(userId);
        return "redirect:/admin";
    }

    // 게시글 삭제
    @PostMapping("/admin/deletePost")
    public String deletePost(@RequestParam String postId) {
        srDao.deletePost(postId);
        return "redirect:/admin";
    }
    


}
