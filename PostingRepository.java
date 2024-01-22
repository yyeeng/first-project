package com.hompage.login.repository;

import com.hompage.login.domain.Member;
import com.hompage.login.domain.Posting;

import java.util.List;
import java.util.Optional;

public interface PostingRepository{
    Posting save(Posting posting);
    Optional<Posting>findById(Long id);
    Optional<Posting>findByTitle(String title);
    Optional<Posting> findByAuthor(String author);
    List<Posting> findAll();


}
