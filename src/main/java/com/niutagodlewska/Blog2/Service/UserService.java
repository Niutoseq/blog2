package com.niutagodlewska.Blog2.Service;

import com.niutagodlewska.Blog2.Models.MyUserDetail;
import com.niutagodlewska.Blog2.Models.UserDTO;
import com.niutagodlewska.Blog2.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDTO> user = userRepo.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        return user.map(MyUserDetail::new).get();
    }

    public boolean userExists(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

}
