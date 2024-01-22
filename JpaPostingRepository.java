package com.hompage.login.repository;

import com.hompage.login.domain.Member;
import com.hompage.login.domain.Posting;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaPostingRepository implements PostingRepository {

    private final EntityManager em;

    public JpaPostingRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public Posting save(Posting posting) {
        em.persist(posting);
        return posting;
    }


    @Override
    public Optional<Posting> findByTitle(String title) {
        List<Posting> result = em.createQuery("select m from Posting m where m.title=:title", Posting.class)
                .setParameter("title", title)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Posting> findById(Long id) {
        Posting posting = em.find(Posting.class, id);
        return Optional.ofNullable(posting);
    }

    @Override
    public Optional<Posting> findByAuthor(String author) {
        List<Posting> result = em.createQuery("select m from Posting m where m.author=:author", Posting.class)
                .setParameter("author", author)
                .getResultList();

        return result.stream().findAny();}
    @Override
    public List<Posting> findAll() {
        return em.createQuery("select m from Posting m", Posting.class)
                .getResultList();
    }
}
