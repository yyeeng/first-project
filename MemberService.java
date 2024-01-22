package com.hompage.login.service;

import com.hompage.login.domain.Member;
import com.hompage.login.repository.MemberRepository;
import com.hompage.login.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


   @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByUserId(member.getUserid())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }

    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(String memberId) {
        return memberRepository.findByUserId(memberId);
    }

    public Member findByUserIdAndPassword(String userid, String password) {
        return memberRepository.findByUserIdAndPassword(userid, password)
                .orElse(null); // orElse를 사용하여 Optional에서 Member 객체를 꺼냅니다.
    }
    // 회원 정보 수정 메서드
    @Transactional
    public void updateMember(Member member) {
        // 기존의 정보를 데이터베이스에서 가져옴
        Optional<Member> existingMember = memberRepository.findById(member.getId());

        // 데이터베이스에 해당 회원이 존재하면 정보를 업데이트하고 저장
        existingMember.ifPresent(m -> {


            m.setName(member.getName() != null && !member.getName().isEmpty() ? member.getName() : m.getName());
            m.setEmail(member.getEmail() != null && !member.getEmail().isEmpty() ? member.getEmail() : m.getEmail());
            m.setUserid(member.getUserid() != null && !member.getUserid().isEmpty() ? member.getUserid() : m.getUserid());
            m.setPassword(member.getPassword() != null && !member.getPassword().isEmpty() ? member.getPassword() : m.getPassword());
            memberRepository.save(m);
        });
    }


}
