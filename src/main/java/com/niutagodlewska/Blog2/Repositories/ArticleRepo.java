package com.niutagodlewska.Blog2.Repositories;

import com.niutagodlewska.Blog2.Models.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepo extends CrudRepository<Article, Long> {
}
