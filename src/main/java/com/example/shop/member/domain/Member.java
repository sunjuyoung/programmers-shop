package com.example.shop.member.domain;


import com.example.shop.member.application.dto.MemberUpdateCommand;
import com.example.shop.member.presentation.dto.MemberUpdateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "유저 정보 엔티티")
@Entity
@Getter
@ToString
@Table(name = "member", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @Schema(description = "유저 고유 아이디", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;

    @Column(unique = true, length = 50)
    private String email;

    @Column(name = "password", nullable = false , length = 50)
    private String password;

    @Column(length = 20)
    private String nickname;

    @Column(nullable = false, length = 20,unique = true)
    private String phone;

    @Schema(description = "등록자 아이디")
    @Column(name = "reg_id", nullable = false)
    private UUID regId;

    @Column(name = "reg_date_time", nullable = false)
    private LocalDateTime regDatetime;

    @Schema(description = "수정자 아이디")
    @Column(name = "modify_id", nullable= false)
    private UUID modifyId;

    @Column(name = "modify_date_time", nullable = false)
    private LocalDateTime   modifyDatetime;

    @Schema(description = "솔트 키")
    @Column(name = "salt_key", nullable = false, length = 14)
    private String saltKey;

    @Schema(description = "회원 상태 플래그")
    @Column(name = "flag", length = 5)
    private String flag;

    private Member(UUID id,
                  String email,
                  String nickname,
                  String password,
                  String phone,
                  String saltKey,
                  String flag) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.saltKey = saltKey;
        this.flag = flag;
    }

    private Member(String id,
                  String email,
                  String nickname,
                  String password,
                  String phone,
                  String saltKey,
                  String flag) {
        this.id = UUID.fromString(id);
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.saltKey = saltKey;
        this.flag = flag;
    }

    //정적 생성자 메서드 (팩토리 메서드)
    public static Member create(String email,
                                String nickname,
                                String password,
                                String phone,
                                String saltKey,
                                String flag

    ) {
        return new Member(
                UUID.randomUUID(),
                email,
                nickname,
                password,
                phone,
                saltKey,
                flag
        );
    }


    //회원 정보 수정
    //닉네임, 전화번호 수정
    public void updateInfo(MemberUpdateCommand command) {
        if(getFlag().equals("N")) {
            throw new IllegalStateException("탈퇴한 회원은 정보를 수정할 수 없습니다.");
        }
        this.nickname = command.nickname();
        this.phone = command.phone();
    }

    //password 변경
    public void changePassword(String newPassword) {
        if(getFlag().equals("N")) {
            throw new IllegalStateException("탈퇴한 회원은 비밀번호를 변경할 수 없습니다.");
        }
        this.password = newPassword;
    }

    //탈퇴 처리
    public void deactivate() {
        //이미 N 상태인경우 예외처리
        if(getFlag().equals("N")) {
            throw new IllegalStateException("이미 탈퇴한 회원입니다.");
        }
        this.flag = "N";
    }

    //탈퇴 취소
    public void activate() {
        //이미 Y 상태인경우 예외처리
        if(getFlag().equals("Y")) {
            throw new IllegalStateException("이미 활성화된 회원입니다.");
        }
        this.flag = "Y";
    }



    @PrePersist
    public void prePersist() {
        if (regId == null) {
            regId = id != null ? id : UUID.randomUUID();
        }
        if (modifyId == null) {
            modifyId = regId;
        }
        if (regDatetime == null) {
            regDatetime = LocalDateTime.now();
        }
        if (modifyDatetime == null) {
            modifyDatetime = LocalDateTime.now();
        }
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @PreUpdate
    public void preUpdate() {
        modifyDatetime =LocalDateTime.now();
        if (modifyId == null) {
            modifyId = id;
        }
    }
}
