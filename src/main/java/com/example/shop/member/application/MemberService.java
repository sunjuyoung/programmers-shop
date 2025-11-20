package com.example.shop.member.application;

import com.example.shop.common.ResponseEntity;
import com.example.shop.member.application.dto.MemberCommand;
import com.example.shop.member.application.dto.MemberInfo;
import com.example.shop.member.application.dto.MemberUpdateCommand;
import com.example.shop.member.domain.Member;
import com.example.shop.member.domain.MemberRepository;
import com.example.shop.member.infrastructure.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public ResponseEntity<List<MemberInfo>> getAllMembers(Pageable pageable) {

        Page<Member> page = memberRepository.findAll(pageable);

        List<MemberInfo> list = page.stream().map(MemberInfo::from).toList();

        return new ResponseEntity<>(HttpStatus.OK.value(), list, list.size());
    }

    public ResponseEntity<MemberInfo> create(MemberCommand command) {

        Member newMember =  Member.create(
                command.email(),
                command.nickname(),
                command.password(),
                command.phone(),
                command.saltKey(),
                command.flag()
        );

        Member save = memberRepository.save(newMember);
        MemberInfo memberInfo = MemberInfo.from(save);

        return new ResponseEntity<>(HttpStatus.CREATED.value(),memberInfo, 1);
    }


    public ResponseEntity<MemberInfo> update(String id, MemberUpdateCommand command) {

        Member member = memberRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("해당 아디를 가진 회원이 없습니다. id:" + id));

        member.updateInfo(command);

        Member save = memberRepository.save(member);
        MemberInfo memberInfo = MemberInfo.from(save);

        return new ResponseEntity<>(HttpStatus.OK.value(),memberInfo,1);
    }

    public ResponseEntity<?> delete(String id) {

        memberRepository.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.OK.value(), id,0);
    }
}
