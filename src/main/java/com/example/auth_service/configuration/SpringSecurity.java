package com.example.auth_service.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity  {  //cross side request forgery -> open at same time . cors

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//                http
//               .securityMatcher(anyRequest -> true) // Match all requests
//               .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())  //call back
//               .csrf(csrf -> csrf.disable()); // Disable CSRF the new way



       return http.
               csrf(csrf->csrf.disable())
               .authorizeHttpRequests(auth->auth
                       .requestMatchers("/api/v1/auth/signup/*") .permitAll()
                       .requestMatchers("/api/v1/auth/signin/*") .permitAll()
               )
               .build(); //secured all paths
   }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

    return new BCryptPasswordEncoder();
    }

}
