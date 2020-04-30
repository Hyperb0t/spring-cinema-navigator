package ru.itis.springcinemanavigator.security;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.springcinemanavigator.dao.UserRepository;
import ru.itis.springcinemanavigator.models.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Service(value = "customUserDetailService")
@NoArgsConstructor
@Transactional
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByEmail(s);
        if(userOptional.isPresent()) {
            return new ru.itis.springcinemanavigator.security.UserDetailsImpl(userOptional.get());
        }
        else {
            throw new UsernameNotFoundException("user not found");
        }
    }
}
