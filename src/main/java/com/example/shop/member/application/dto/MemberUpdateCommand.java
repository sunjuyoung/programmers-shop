package com.example.shop.member.application.dto;

public record MemberUpdateCommand(
        String nickname,
        String phone
) {
}
