package com.example.shop.member.presentation;

import com.example.shop.common.ResponseEntity;
import com.example.shop.member.application.MemberService;
import com.example.shop.member.application.dto.MemberInfo;
import com.example.shop.member.domain.Member;
import com.example.shop.member.presentation.dto.MemberRequest;
import com.example.shop.member.presentation.dto.MemberUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<List<MemberInfo>> findAll(Pageable pageable){

        PageRequest page = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("id").ascending());

        return memberService.getAllMembers(page);
    }

    @Operation(
        summary = "회원 등록",
        description = "새로운 회원을 public.member테이블에  등록합니다."
    )
    @PostMapping
    public ResponseEntity<MemberInfo> createMember(@RequestBody MemberRequest member){

        return memberService.create(member.toCommand());
    }

    @Operation(
        summary = "회원 수정",
        description = "기존 회원의 정보를 수정합니다."
    )
    @PutMapping("{id}")
    public ResponseEntity<MemberInfo> updateMember(@PathVariable String id, @RequestBody MemberUpdateRequest request){

        return  memberService.update(id, request.toMemberUpdateCommand());
    }


    @Operation(
        summary = "회원 삭제",
        description = "기존 회원의 정보를 삭제합니다."
    )
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMember(@PathVariable String id){

      return  memberService.delete(id);
    }


}
