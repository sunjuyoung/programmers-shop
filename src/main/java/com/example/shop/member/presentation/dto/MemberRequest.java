package com.example.shop.member.presentation.dto;

import com.example.shop.member.application.dto.MemberCommand;

public record MemberRequest(
        String email,
        String nickname,
        String password,
        String phone,
        String saltKey,
        String flag
) {

    public MemberCommand toCommand() {
        return new MemberCommand(
                this.email,
                this.nickname,
                this.password,
                this.phone,
                this.saltKey,
                this.flag
        );
    }
}