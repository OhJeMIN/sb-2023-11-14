package com.ll.sb20231114.domain.home.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
        // 이 함수의 리턴 값을 그대로 브라우저에 전송하라는 의미
    String goToArticleList() {
        return "redirect:/article/list";
    }

}