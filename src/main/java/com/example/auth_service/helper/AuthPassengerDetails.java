package com.example.auth_service.helper;

import com.example.auth_service.model.Passenger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//why this class ...?
// sc works on UserDetails polymorphic type for auth
//convert your type to my polymorphic type ..
// auth passenger details in the form of userDetails ...
public class AuthPassengerDetails extends Passenger implements UserDetails {


    private String username;
    private String password;

   public AuthPassengerDetails(Passenger passenger){
       this.username = passenger.getName();
       this.password = passenger.getPassword();

   }

   //......sc does authentication on userdetails


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public String getPassword(){
       return this.password;
    }
    

    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return true;
    }
}
