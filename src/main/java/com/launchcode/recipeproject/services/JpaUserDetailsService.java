package com.launchcode.recipeproject.services;

import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.models.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // creates an instance
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //    UserDetails Object
//    Provides core user information.
//    Implementations are not used directly by Spring Security for security purposes.
//    They simply store user information which is later encapsulated into Authentication objects.
//    This allows non-security related user information (such as email addresses, telephone numbers etc)
//    to be stored in a convenient location.

    @Override // when a user logs in, spring security will pass user to security user
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username) // search for username
                .map(SecurityUser::new) // map <Optional> User to a new SecurityUser object
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username)); // throw an error

    }
}
