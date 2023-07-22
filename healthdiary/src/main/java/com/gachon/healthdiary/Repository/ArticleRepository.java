package com.gachon.healthdiary.Repository;

import com.gachon.healthdiary.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
