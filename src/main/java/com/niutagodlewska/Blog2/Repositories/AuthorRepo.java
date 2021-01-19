package com.niutagodlewska.Blog2.Repositories;

import com.niutagodlewska.Blog2.Models.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Long> {
}
