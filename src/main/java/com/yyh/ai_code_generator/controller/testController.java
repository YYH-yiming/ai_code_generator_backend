package com.yyh.ai_code_generator.controller;

import com.yyh.ai_code_generator.common.BaseResponse;
import com.yyh.ai_code_generator.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {

    @GetMapping("/")
    public BaseResponse<String> test(){
        return ResultUtils.success("hello! this is a test");
    }
}
