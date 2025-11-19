package com.example.shop.controller;

import com.example.shop.member.Member;
import com.example.shop.member.MemberRepository;
import com.example.shop.member.MemberRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.v1}/members")
@RequiredArgsConstructor
public class MemberController {


    private final MemberRepository memberRepository;

    @Operation(
        summary = "모든 회원 조회",
        description = "public.member 테이블 등록된 모든 회원의 정보를 조회합니다."
    )
    @GetMapping
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    @Operation(
        summary = "회원 등록",
        description = "새로운 회원을 public.member테이블에  등록합니다."
    )
    @PostMapping
    public Member createMember(@RequestBody MemberRequest member){
        Member newMember = new Member(
            UUID.randomUUID(),
                member.email(),
                member.nickname(),
                member.password(),
                member.phone(),
                member.saltKey(),
                member.flag()
        );
        return memberRepository.save(newMember);
    }



    @Operation(
        summary = "회원 수정",
        description = "기존 회원의 정보를 수정합니다."
    )
    @PutMapping("{id}")
    public Member updateMember(@PathVariable String id, @RequestBody MemberRequest request){
        Member member = new Member(
                id,
                request.email(),
                request.nickname(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );
        return memberRepository.save(member);
    }


    @Operation(
        summary = "회원 삭제",
        description = "기존 회원의 정보를 삭제합니다."
    )
    @DeleteMapping("{id}")
    public void deleteMember(@PathVariable String id){
        memberRepository.deleteById(UUID.fromString(id));
    }


}
