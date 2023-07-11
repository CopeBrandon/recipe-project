package com.launchcode.recipeproject.config;

import com.launchcode.recipeproject.services.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JpaUserDetailsService jpaUserDetailsService; // annotated with @Service, load with constructor injection

    public SecurityConfiguration(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean // instantiated, assembled, and managed by Spring
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // lambda ->  separates parameters (left-side) from the implementation (right side) //Primary Syntax : (parameters) -> { statements; }
        return http
                .csrf().disable() // disables the need for web tokens
                .authorizeRequests( auth -> auth
                        .mvcMatchers("/**", "/","/register","/login","/oauth/**").permitAll() // add permitted folders here "/**" to turn off auth
                        .anyRequest().authenticated() // authenticate all other requests
                        )
                .userDetailsService(jpaUserDetailsService) // this is where spring security looks up the user and imports a SecurityUser
//                .httpBasic(Customizer.withDefaults())  // default login
                .formLogin(form -> form // custom form
                        .loginPage("/login")
                        .permitAll())
//                .oauth2Login(Customizer.withDefaults())
                .oauth2Login(form -> form // custom form
                        .loginPage("/login")
////                        .userInfoEndpoint()
                        )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/"))
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
