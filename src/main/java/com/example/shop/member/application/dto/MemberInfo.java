package com.example.shop.member.application.dto;

import com.example.shop.member.domain.Member;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberInfo(
        UUID id,
        String email,
        String nickname,
        String phone,
        String saltKey,
        String flag,
        LocalDateTime regDatetime,
        LocalDateTime modifyDatetime
) {

    public static MemberInfo from(Member member) {
        return new MemberInfo(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getPhone(),
                member.getSaltKey(),
                member.getFlag(),
                member.getRegDatetime(),
                member.getModifyDatetime()
        );
    }
}
