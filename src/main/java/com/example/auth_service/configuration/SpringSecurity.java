package com.example.auth_service.configuration;

import com.example.auth_service.services.UserDetailsserviceImpl;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity  {  //cross side request forgery -> open at same time . cors

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsserviceImpl();
    }

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
   public AuthenticationProvider authenticationProvider (){
       //imp -> actual logic to authenticate the user ...
       // we are going to use DAO auth provider ..
       // convert passesnger to compatible authPassesnger
       // access to this obj is done by userDetaService

       DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
       authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
//       DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
       return authenticationProvider;
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return  configuration.getAuthenticationManager();

   }

   @Bean
   public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
   }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){

    return new BCryptPasswordEncoder();
    }

}
