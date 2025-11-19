package com.example.shop.service;

import com.example.shop.common.ResponseEntity;
import com.example.shop.member.Member;
import com.example.shop.member.MemberRequest;
import com.example.shop.member.MemberUpdateRequest;

import java.util.List;

public interface MemberService {

    ResponseEntity<List<Member>> getAllMembers();

    ResponseEntity<Member> create(MemberRequest request);

    ResponseEntity<Member> update(String id, MemberUpdateRequest request);

    String delete(String memberId);


}
