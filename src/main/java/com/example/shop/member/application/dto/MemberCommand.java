package com.example.shop.member.application.dto;

/**
컨트롤러 등 외부 계층 HTTP 요청을 직접 전달하지 않고 DTO를 통해 전달하기 위해 사용
애플케이션 서비스에 전달
*/
public record MemberCommand(
        String email,
        String nickname,
        String password,
        String phone,
        String saltKey,
        String flag
) {
}
