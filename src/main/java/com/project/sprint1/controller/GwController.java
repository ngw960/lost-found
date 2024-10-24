package com.project.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.sprint1.dao.GwDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class GwController {

    @Autowired
    private GwDao gwDao;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/password")
    public String findPasswordPage() {
        return "password";
    }

    @GetMapping("/check/findPw")
    @ResponseBody
    public Map<String, Boolean> checkUser(@RequestParam String id, @RequestParam String email) {
        boolean exists = gwDao.checkUserExists(id, email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("check", exists);
        return response;
    }

    @PostMapping("/check/findPw/sendEmail")
    @ResponseBody
    public void sendTempPassword(@RequestParam String id, @RequestParam String email) {
        String newPassword = generateRandomPassword();
        gwDao.updatePassword(id, newPassword);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Lucky Pynk에서 임시 비밀번호를 알려드립니다");
        message.setText(id + "님의 임시 비밀번호는 " + newPassword + " 입니다.");
        try {
            javaMailSender.send(message);
            System.out.println("메일 발송 성공");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("메일 발송 실패: " + e.getMessage());
        }
    }

    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}
