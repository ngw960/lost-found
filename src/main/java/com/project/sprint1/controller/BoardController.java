package com.project.sprint1.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.sprint1.dao.BoardDao;
import com.project.sprint1.dao.PostDao;

import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
public class BoardController {
    @Autowired
    BoardDao boardDao;
    @Autowired
    PostDao postDao;

    @GetMapping("board/insert") // 글쓰기 페이지 주소
    public String boardInsert(HttpSession session,
            Model model) {
        model.addAttribute("id", session.getAttribute("id"));
        // ----------------------수정0707-----------------------
        List<Map<String, Object>> category = boardDao.category();
        model.addAttribute("category", category);
        // ----------------------------------------------------
        return "board/insert"; // 글쓰기 페이지 html파일
    }

    // 글쓰기 페이지에 위치 등록하기위한 페이지 팝업창
    @GetMapping("board/location")
    public String boardLocation(@RequestParam(defaultValue = "") String dong,
            Model model) {
        // 주소 테이블 가져오기
        List<Map<String, Object>> locList = boardDao.location(dong);
        model.addAttribute("locList", locList);
        return "board/location";
    }

    @PostMapping("board/insert/action") // 글쓰기 페이지에서 보낸 값 다루기
    public String boardInsertAction(@RequestParam String id,
            @RequestParam String code, // 글쓰기 페이지에서 보낸 input 값 받기
            @RequestParam String title,
            @RequestParam String year, @RequestParam String month, @RequestParam String day,
            @RequestParam String location1,
            @RequestParam String location2,
            @RequestParam String lon,
            @RequestParam String lat,
            @RequestParam MultipartFile image,
            @RequestParam String category,
            @RequestParam String itemName,
            @RequestParam String content,
            HttpSession session) throws IOException {
        String date = year + "년" + month + "월" + day + "일";

        String uploadPath = new File("src/main/resources/static/img/").getAbsolutePath();
        String filePath;

        // 디렉토리가 존재하지 않으면 생성
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!image.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String fileName = uuid.toString() + "_" + image.getOriginalFilename();
            String fullPath = uploadPath + File.separator + fileName;
            filePath = "/img/" + fileName;
            File saveFile = new File(fullPath);
            image.transferTo(saveFile);
        } else {
            // 기본 이미지 경로 설정
            filePath = "/img/NO.png"; // 사진 파일을 넣지 않았을때 출력하는 이미지(기본 이미지)
        }

        boardDao.boardInsert(id, code, title, date, location1, location2, lon, lat, filePath, category, itemName,
                content);

        return "redirect:/main";
    }

    @GetMapping("board/category")
    public String categorySelect(Model model,
            @RequestParam String catCode) {
        List<Map<String, Object>> categoryDetail = boardDao.categoryDetail(catCode);
        model.addAttribute("categoryDetail", categoryDetail);

        return "board/category";
    }

    // 게시글 상세 조회
    @GetMapping("detail")
    public String postDetail(@RequestParam int seq, Model model, HttpSession session) {

        // 2024-07-08
        // 로그인 여부 확인
        if (session.getAttribute("id") == null) {
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        // 게시글 상세 정보
        Map<String, Object> post = postDao.postDetail(seq);

        // 댓글 정보
        List<Map<String, Object>> comments = postDao.commentSelect(seq);
        model.addAttribute("post", post);
        model.addAttribute("commentSet", comments);

        // 0709 조회수 +1
        boardDao.cntIncrease(seq);

        model.addAttribute("userId", session.getAttribute("id"));
        return "board/detail"; // 게시글 상세보기 페이지
    }

    // 2024-07-08
    // 게시글 수정
    @PostMapping("/board/detail/update")
    public String postUpdate(@RequestParam int seq,
            @RequestParam String title,
            @RequestParam String content,
            HttpSession session) {
        // 로그인 여부 확인 // 계속 추가해줘야 됨.
        String userId = (String) session.getAttribute("id");
        if (userId == null) {
            return "redirect:/login";
        }

        // 게시글 수정
        postDao.updatePost(seq, title, content);

        return "redirect:/detail?seq=" + seq;
    }

    // 2024-07-08
    // 게시글 삭제
    @GetMapping("/board/detail/delete")
    public String postDelete(@RequestParam int seq, HttpSession session) {
        // 로그인 여부 확인
        String userId = (String) session.getAttribute("id");
        if (userId == null) {
            return "redirect:/login";
        }

        // 게시글 삭제
        postDao.deletePost(seq);

        return "redirect:/main";
    }

    // 댓글 작성
    @PostMapping("board/comment")
    public String addComment(@RequestParam String postSeq, @RequestParam String userId, @RequestParam String content) {
        postDao.insertComment(postSeq, userId, content);
        return "redirect:/detail?seq=" + postSeq; // 댓글 작성 후 게시글 상세보기 페이지로 리다이렉트
    }

    // 댓글 삭제
    @GetMapping("/board/comment/delete")
    public String boardCommentEdit(@RequestParam String commentseq, @RequestParam String seq) {
        postDao.deleteComment(commentseq);
        return "redirect:/detail?seq=" + seq;
    }

    // 댓글 수정
    @GetMapping("/board/comment/update")
    public String boardCommentEdit(@RequestParam String commentseq,
            @RequestParam String newContent,
            @RequestParam String seq) {
        postDao.updateComment(newContent, commentseq);
        return "redirect:/detail?seq=" + seq;
    }

}
