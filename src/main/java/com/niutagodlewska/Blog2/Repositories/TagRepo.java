package com.niutagodlewska.Blog2.Repositories;


import com.niutagodlewska.Blog2.Models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends CrudRepository<Tag, Long>{
}
