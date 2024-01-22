package com.hompage.login;

import com.hompage.login.repository.*;
import com.hompage.login.service.MemberService;
import com.hompage.login.service.PostingService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class SpringConfig {
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;}

    @Bean
    public MemberService memberService() {
            return new MemberService(memberRepository());}
    @Bean
    public MemberRepository memberRepository() {
            return new JpaMemberRepository(em);}
    @Bean
    public PostingService postingService() {
        return new PostingService(postingRepository());}
    @Bean
    public PostingRepository postingRepository() {
        return new JpaPostingRepository(em);}




}
