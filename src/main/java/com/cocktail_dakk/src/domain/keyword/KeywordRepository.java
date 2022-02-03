package com.cocktail_dakk.src.domain.keyword;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Page<Keyword> findAllByKeywordNameContaining(Pageable pageable, String keywordName);
    Keyword findByKeywordName(String name);
}
