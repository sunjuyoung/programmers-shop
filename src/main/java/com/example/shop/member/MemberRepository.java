package com.example.shop.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface MemberRepository extends JpaRepository<Member, UUID> {


}
