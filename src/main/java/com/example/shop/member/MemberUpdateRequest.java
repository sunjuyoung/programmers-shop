package com.example.shop.member;

public record MemberUpdateRequest(
        String nickname,
        String phone
) {
}
