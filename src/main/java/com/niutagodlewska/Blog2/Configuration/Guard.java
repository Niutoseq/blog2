package com.niutagodlewska.Blog2.Configuration;


import com.niutagodlewska.Blog2.Models.UserDTO;
import com.niutagodlewska.Blog2.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Guard {

    @Autowired
    private UserRepo userRepo;

    public boolean checkUserId(Authentication authentication, long id){
        String name = authentication.getName();
        Optional<UserDTO> result = userRepo.findByUsername(name);
        UserDTO res = result.get();
        return res != null && res.getId()==id;
    }
}
