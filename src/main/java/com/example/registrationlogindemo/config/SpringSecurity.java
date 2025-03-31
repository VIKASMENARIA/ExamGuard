package com.example.registrationlogindemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
/*
 @Configuration: Indicates that the class can be used by the Spring IoC container as a source of bean definitions. 
 It is a part of the Spring Framework.
@EnableWebSecurity: Enables Spring Security’s web security support and provides the Spring MVC integration.*/
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        /*This method defines a PasswordEncoder bean that uses BCryptPasswordEncoder to encode passwords.
         *  BCrypt is a strong hashing function that is recommended for password storage.*/
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/register/**", "/index", "/login", "/quiz").permitAll() // Allow public access to these pages
                                .requestMatchers("/users").hasRole("ADMIN") // Restrict "users" to admin role
                                .anyRequest().authenticated() // Protect all other endpoints
                )
                .formLogin(
                        form -> form
                                .loginPage("/login") // Custom login page
                                .loginProcessingUrl("/login") // URL to submit login form
                                .defaultSuccessUrl("/quiz", true) // Redirect to test on successful login
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login?logout") // Redirect to login page after logout
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService) // Use custom UserDetailsService
                .passwordEncoder(passwordEncoder()); // Encode passwords using BCrypt
    }
}




