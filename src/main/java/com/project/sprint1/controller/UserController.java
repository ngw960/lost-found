package com.project.sprint1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.sprint1.dao.UserDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public String loginAction(@RequestParam String id,
            @RequestParam String pw,
            HttpSession session,
            Model model) {
        int cnt = userDao.checkUser(id, pw);
        if (cnt > 0) {
            // 세션에 아이디 저장
            session.setAttribute("id", id);
            // 로그인 성공 후 메인페이지로 이동
            return "redirect:/main";

        } else {
            // 로그인 실패 시 에러 메시지 처리
            model.addAttribute("error", "아이디 또는 비밀번호를 확인해주세요");
            return "login";
        }

    }

    @GetMapping("signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("signup/action")
    public String signupAction(@RequestParam String id,
            @RequestParam String pw,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String email,
            Model model) {

        // 회원가입 시 모든 필드가 입력되었는지 확인 - signup.html 에 javascript 로 구현

        if (userDao.dupIdCheck(id) > 0) {
            model.addAttribute("dupcheck", "이미 사용 중인 아이디입니다.");
            return "redirect:/signup"; // 이미 존재하는 ID일 경우 다시 회원가입 페이지
        }
        // 비밀번호와 비밀번호 확인이 일치하는지 확인 - signup.html 에 javascript 로 구현

        userDao.signupAction(id, pw, name, phone, email);
        model.addAttribute("dupcheck", "사용 가능한 아이디입니다..");
        return "redirect:/login"; // 회원가입하면 로그인 페이지로 돌아가서 재로그인 하게 함
    }

    @GetMapping("dupcheck") // ID 중복체크
    @ResponseBody
    public Map<String, Object> dupCheck(@RequestParam String id) {
        int dup = userDao.dupIdCheck(id);
        Map<String, Object> response = new HashMap<>();
        response.put("isDup", dup > 0);
        return response;
    }

}
