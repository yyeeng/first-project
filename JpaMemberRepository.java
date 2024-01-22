package com.hompage.login.repository;

import com.hompage.login.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByUserIdAndPassword(String userid, String password) {
        List<Member> result = em.createQuery("select m from Member m where m.userid = :userid and m.password = :password", Member.class)
                .setParameter("userid", userid)
                .setParameter("password", password)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByUserId(String userid) {
        List<Member> result = em.createQuery("select m from Member m where m.userid=:userid", Member.class)
                .setParameter("userid", userid)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public void update(Member member) {
        // JPA의 EntityManager를 사용하여 멤버 정보 업데이트
        em.merge(member);
    }

    public void updateMember(Long memberId, Member member) {
        em.merge(member);
    }
}
