package com.example.shop.member;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "member", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private UUID id;

    @Column(unique = true, length = 50)
    private String email;

    @Column(name = "password", nullable = false , length = 50)
    private String password;

    @Column(length = 20)
    private String nickname;

    @Column(nullable = false, length = 20,unique = true)
    private String phone;

    @Column(name = "reg_id", nullable = false)
    private UUID regId;

    @Column(name = "reg_date_time", nullable = false)
    private LocalDateTime regDatetime;

    @Column(name = "modify_id", nullable= false)
    private UUID modifyId;

    @Column(name = "modify_date_time", nullable = false)
    private LocalDateTime   modifyDatetime;

    @Column(name = "salt_key", nullable = false, length = 14)
    private String saltKey;

    @Column(name = "flag", length = 5)
    private String flag;

    public Member(UUID id,
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

    public Member(String id,
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

    //update method
    public static Member update(MemberRequest request, String id){
        return new Member(
                UUID.fromString(id),
                request.email(),
                request.nickname(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );
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
