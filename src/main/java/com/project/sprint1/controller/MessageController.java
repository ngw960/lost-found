package com.project.sprint1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.sprint1.dao.MessageDao;

import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
public class MessageController {
    @Autowired
    MessageDao messageDao;

    @GetMapping("message")
    public String message(HttpSession session,
                          Model model){
        String userId = session.getAttribute("id").toString();
        List<Map<String,Object>> userList = messageDao.userSelect(userId);
        model.addAttribute("sessionId", userId);
        model.addAttribute("userList", userList);
        
        int messageCnt = messageDao.messageCnt(userId);
        if(messageCnt > 0){
            model.addAttribute("messageCnt", "❗");
        }

        return "message/message";
    }

    @GetMapping("message/send")
    public String messageSend(@RequestParam String userid,
                              HttpSession session,
                              Model model){
        model.addAttribute("userid", userid);
        model.addAttribute("sessionid", session.getAttribute("id"));
        return "message/message_insert";
    }
}