package com.niutagodlewska.Blog2.Repositories;

import com.niutagodlewska.Blog2.Models.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserDTO, Long> {
    Optional<UserDTO> findByUsername(String username);
}
