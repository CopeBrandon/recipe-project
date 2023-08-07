package com.launchcode.recipeproject.config;

import com.launchcode.recipeproject.services.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JpaUserDetailsService jpaUserDetailsService; // annotated with @Service, load with constructor injection
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfiguration(JpaUserDetailsService jpaUserDetailsService, AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Bean // instantiated, assembled, and managed by Spring
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // lambda ->  separates parameters (left-side) from the implementation (right side) //Primary Syntax : (parameters) -> { statements; }
        return http
                .csrf().disable() // disables the need for web tokens
                .authorizeRequests( auth -> auth
                        .mvcMatchers("/**", "/","/register","/login","/oauth/**").permitAll() //TODO add permitted folders here "/**" to turn off auth
                        .anyRequest().authenticated() // authenticate all other requests
                        )
                .userDetailsService(jpaUserDetailsService) // this is where spring security looks up the user and imports a SecurityUser
//                .httpBasic(Customizer.withDefaults())  // default login
                .formLogin(form -> form // custom form
                        .loginPage("/login")
                        .successHandler(authenticationSuccessHandler)
                        .permitAll())
//                .oauth2Login(Customizer.withDefaults()) // default oauth2
                .oauth2Login(form -> form // custom form
                        .loginPage("/login")
                        .successHandler(authenticationSuccessHandler) // set up a User with OAuth2 data
                        )
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
