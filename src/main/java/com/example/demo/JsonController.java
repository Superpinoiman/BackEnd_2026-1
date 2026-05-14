package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;
import java.util.LinkedHashMap;

@Controller
public class JsonController {

    @ResponseBody
    @GetMapping("/json")
    public Map<String, Object> json() {
        Map<String, Object> data = new LinkedHashMap<>();

        data.put("age", 24);
        data.put("name", "장지훈");

        return data;
    }
}
