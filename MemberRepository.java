package com.hompage.login.repository;

import com.hompage.login.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByUserIdAndPassword(String userid, String password);
    Optional<Member> findById(Long id);
    Optional<Member> findByUserId(String userid);
    Optional<Member> findByName(String name);
    List<Member> findAll();

    void update(Member member);
}
