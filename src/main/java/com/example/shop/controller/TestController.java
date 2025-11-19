package com.example.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "테스트", description = "서버 연결 테스트 API")
@RestController
public class TestController {

    @Operation(summary = "서버 연결 테스트", description = "서버가 정상적으로 작동하는지 확인하는 API입니다.")
    @GetMapping("/test")
    public String test() {
        return "Test successful";
    }
}
