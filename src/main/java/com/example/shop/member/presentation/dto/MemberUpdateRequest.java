package com.example.shop.member.presentation.dto;

import com.example.shop.member.application.dto.MemberCommand;
import com.example.shop.member.application.dto.MemberInfo;
import com.example.shop.member.application.dto.MemberUpdateCommand;

public record MemberUpdateRequest(
        String nickname,
        String phone
) {

    //MemberCommand
    public MemberUpdateCommand toMemberUpdateCommand(){
        return new MemberUpdateCommand(nickname, phone);
    }
}
