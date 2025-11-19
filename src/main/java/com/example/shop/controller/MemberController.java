package com.example.shop.controller;

import com.example.shop.common.ResponseEntity;
import com.example.shop.member.Member;
import com.example.shop.member.MemberRepository;
import com.example.shop.member.MemberRequest;
import com.example.shop.member.MemberUpdateRequest;
import com.example.shop.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("${api.v1}/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @Operation(
        summary = "모든 회원 조회",
        description = "public.member 테이블 등록된 모든 회원의 정보를 조회합니다."
    )
    @GetMapping
    public ResponseEntity<List<Member>> findAll(){

         return memberService.getAllMembers();
    }

    @Operation(
        summary = "회원 등록",
        description = "새로운 회원을 public.member테이블에  등록합니다."
    )
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody MemberRequest member){

        return memberService.create(member);
    }



    @Operation(
        summary = "회원 수정",
        description = "기존 회원의 정보를 수정합니다."
    )
    @PutMapping("{id}")
    public ResponseEntity<Member> updateMember(@PathVariable String id, @RequestBody MemberUpdateRequest request){

        return  memberService.update(id, request);
    }


    @Operation(
        summary = "회원 삭제",
        description = "기존 회원의 정보를 삭제합니다."
    )
    @DeleteMapping("{id}")
    public void deleteMember(@PathVariable String id){

        memberService.delete(id);
    }


}
