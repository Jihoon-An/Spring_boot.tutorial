package com.example.demo2.controller;

import com.example.demo2.dto.MemberDTO;
import com.example.demo2.dto.TestDTO;
import com.example.demo2.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class HomeController {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    HttpSession session;

    @RequestMapping("/")
    public String home(Model model) throws Exception {

        MemberDTO member = memberRepository.selectById("abg1418");

        model.addAttribute("member", member);

        return "home";
    }

    @PostMapping("/fileUpload")
    public String fileUpload(String message, MultipartFile[] profileImg) throws IOException {

        // 경로 지정
        String realPath = session.getServletContext().getRealPath("upload");

        // 폴더 생성
        File filePath = new File(realPath);
        if (!filePath.exists()) {
            filePath.mkdir();
        }

        // 파일 이름 설정
        String orgName = profileImg[0].getOriginalFilename();
        String sysName = UUID.randomUUID() + "_" + orgName;

        // 파일 복사
        profileImg[0].transferTo(new File(filePath+"/"+sysName));

        return "redirect:/";
    }

    @PostMapping("/test")
    public String testText(TestDTO testDTO) {

        System.out.println(testDTO.getTestText());

        return "redirect:/";
    }

    @ExceptionHandler
    public String exceptionHandler(Exception e){
        e.printStackTrace();
        return "error";
    }
}
