package com.niutagodlewska.Blog2.Repositories;

import com.niutagodlewska.Blog2.Models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
}
