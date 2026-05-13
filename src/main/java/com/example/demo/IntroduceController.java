package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IntroduceController {

    @GetMapping("/introduce")
    public String introduce() {
        return "introduce";
    }

    @ResponseBody
    @GetMapping(value = "/introduce", params = "name")
    public String introduce(@RequestParam String name) {
        return "안녕하세요 제 이름은 " + name + "입니다!";
    }
}
