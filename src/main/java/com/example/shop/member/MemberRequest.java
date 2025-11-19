package com.example.shop.member;

public record MemberRequest(
        String email,
        String nickname,
        String password,
        String phone,
        String saltKey,
        String flag
) {
}