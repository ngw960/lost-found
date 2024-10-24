package com.project.sprint1.controller;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.sprint1.dao.HRDao;
import com.project.sprint1.dao.MessageDao;

import jakarta.servlet.http.HttpSession;

import java.util.*;

@Controller
public class HRController {
    @Autowired
    HRDao hrDao;
    @Autowired
    MessageDao messageDao;

    // 0709 김혜림 수정
    @GetMapping("message/send/action")
    public String messageSendAction(@RequestParam String sendid,
            @RequestParam String receiveid,
            @RequestParam String messageContent) {
        hrDao.insertMessage(sendid, receiveid, messageContent);
        return "redirect:/message";
    }

    @GetMapping("message/message_notice")
    public String messageNotice(Model model,
            HttpSession session) {
        String id = session.getAttribute("id").toString();
        List<Map<String, Object>> messageList = hrDao.messageSelect(id);
        model.addAttribute("messageList", messageList);

        // 0709 김혜림 코드 추가
        List<Map<String, Object>> messageSendList = hrDao.messageSendSelect(id);
        model.addAttribute("messageSendList", messageSendList);

        return "message/message_notice";
    }

    @GetMapping("mypage")
    public String mypage(HttpSession session,
            Model model) {
        String id = session.getAttribute("id").toString();
        model.addAttribute("id", id);
        Map<String, Object> userInfoSet = hrDao.userInfoSelect(id);
        List<Map<String, Object>> userBaordSet = hrDao.userBoardSelect(id);
        model.addAttribute("userInfoSet", userInfoSet);
        model.addAttribute("userBaordSet", userBaordSet);

        int messageCnt = messageDao.messageCnt(id);
        if (messageCnt > 0) {
            model.addAttribute("messageCnt", "❗");
        }
        return "mypage";
    }

    @GetMapping("delete/user")
    public String deleteUser(HttpSession session,
            @RequestParam String id) {
        hrDao.deleteUser(id);
        session.invalidate();
        return "redirect:/main";
    }

    @GetMapping("update/user/phone")
    public String updateUserPhone(@RequestParam String phone,
            HttpSession session) {
        String userId = session.getAttribute("id").toString();
        hrDao.updatePhone(userId, phone);
        return "redirect:/mypage";
    }

    @GetMapping("update/user/email")
    public String updateUserEmail(@RequestParam String email,
            HttpSession session) {
        String userId = session.getAttribute("id").toString();
        hrDao.updateEmail(userId, email);
        return "redirect:/mypage";
    }

    @GetMapping("update/user/pw")
    public String updateUserPw(@RequestParam String pw,
            HttpSession session) {
        String userId = session.getAttribute("id").toString();
        hrDao.updatePw(userId, pw);
        return "redirect:/mypage";
    }

    // 0709 김혜림 코드 추가
    @GetMapping("message/delete")
    public String messageDelete(@RequestParam String seq) {
        hrDao.messageDelete(seq);
        return "redirect:/message/message_notice";
    }

    // 비밀번호 찾기 기능
    @GetMapping("find-password")
    public String findPassword() {
        return "find-password";
    }

    @GetMapping("show-password")
    public String findShowPassword(@RequestParam String id, Model model) {
        String password = hrDao.findPasswordSelect(id);

        if (password != null) {
            String showpassword = id + " 님의 비밀번호는 " + password + " 입니다.";
            model.addAttribute("showpassword", showpassword);
        } else {
            model.addAttribute("showpassword", id + " 로 가입된 회원이 없습니다.");
        }

        return "find-password";
    }

    // 쪽지 확인
    @GetMapping("message/check")
    public String messageCheck(@RequestParam String seq) {
        hrDao.messageCheckUpdate(seq);
        return "redirect:/message/message_notice";
    }

    // 찾는 중 -> 찾기 완료 -> 찾는 중 -> 찾기 완료
    @GetMapping("find/check")
    public String findCheck(@RequestParam String find, @RequestParam String seq) {
        hrDao.findUpdate(find, seq);
        return "redirect:/mypage";
    }
}
