package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/introduce")
public class IntroduceController {

    @GetMapping
    public String introduce() {
        return "introduce";
    }

    @ResponseBody
    @GetMapping(params = "name")
    public String introduceParam(@RequestParam String name) {
        return "안녕하세요 제 이름은 " + name + "입니다!";
    }
}
