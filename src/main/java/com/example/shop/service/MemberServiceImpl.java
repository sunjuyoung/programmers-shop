package com.example.shop.service;

import com.example.shop.common.ResponseEntity;
import com.example.shop.member.Member;
import com.example.shop.member.MemberRepository;
import com.example.shop.member.MemberRequest;
import com.example.shop.member.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements  MemberService {

    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return new ResponseEntity<>(HttpStatus.OK.value(), members, members.size());
    }

    @Override
    public ResponseEntity<Member> create(MemberRequest request) {
        Member newMember = new Member(
                UUID.randomUUID(),
                request.email(),
                request.nickname(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );

        Member save = memberRepository.save(newMember);
        int cnt = 0;
        if(save instanceof List){
            cnt = ((List<?>) save).size();
        }else {
            cnt = 1;
        }

        return new ResponseEntity<>(HttpStatus.CREATED.value(),save, cnt);
    }


    @Override
    public ResponseEntity<Member> update(String id, MemberUpdateRequest request) {
        Member member = memberRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("해당 아디를 가진 회원이 없습니다. id:" + id));

        member.updateInfo(request);
//        Member member = new Member(
//                id,
//                request.email(),
//                request.nickname(),
//                request.password(),
//                request.phone(),
//                request.saltKey(),
//                request.flag()
//        );
        memberRepository.save(member);
        return new ResponseEntity<>(HttpStatus.OK.value(),member,1);
    }

    @Override
    public ResponseEntity<?> delete(String id) {
        memberRepository.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK.value(), id,0);
    }
}
