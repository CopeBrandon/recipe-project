package com.launchcode.recipeproject.services;

import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.models.User;
import com.nimbusds.jose.proc.SecurityContext;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JpaUserDetailsService jpaUserDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println(authentication.getPrincipal());
        if (authentication.getPrincipal() instanceof DefaultOAuth2User) { // check type instance
            DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal(); // cast user to DefaultOAuth2User
            String username = "";
            if (oAuth2User.getAttribute("email") != null) { // gmail oauth uses email
                username = oAuth2User.getAttribute("email") + "@gmail.com";
            } else if (oAuth2User.getAttribute("login") != null) { // github oauth uses login
                username = oAuth2User.getAttribute("id") + "@github.com";
            }
            User user = jpaUserDetailsService.getUsername(username);
            if (user == null) { // no user exists, create one
                user = new User();
                user.setEmail(username);
                user.setUsername(username);
                user.setPassword(randomString(50)); // generate a default password that is never used
                user.setRoles("ROLE_USER");
                userRepository.save(user);
            }
//            else{
//            jpaUserDetailsService.loadUserByUsername(user.getUsername()); // load User into Spring Security so we can use its ID later.
//        }
            new DefaultRedirectStrategy().sendRedirect(request, response, "/");
        }
    }

    public String randomString(int n){
        String baseString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz" + "!@#$%&*()_+";
        String returnString = "";
        while (returnString.length() < n){
            int index = (int) Math.round(baseString.length() * Math.random());
            returnString += baseString.charAt(index);
        }
        return returnString;
    }
}
