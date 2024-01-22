package com.hompage.login.repository;

import com.hompage.login.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence =0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findByUserIdAndPassword(String userid, String password) {
        return store.values().stream()
                .filter(member -> member.getUserid().equals(userid))
                .filter(member -> member.getPassword().equals(password))
                .findAny();
    }


    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByUserId(String userid) {
        return store.values().stream()
                .filter(member -> member.getUserid().equals(userid))
                .findAny();
    }


    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public void update(Member member) {
        // 구현 내용 추가
    }
}
