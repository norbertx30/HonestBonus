package com.github.groupproject.security;

import com.github.groupproject.entities.Employee;
import com.github.groupproject.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private String email;
    private String password;
    private String role;
    private boolean enabled;


    public UserDetailsImpl(User user) {
        this.email = user.getEmail();
        this.role = "USER";
        this.password = user.getPassword();
        this.enabled = this.password != null;

    }


    public UserDetailsImpl(Employee employee) {
        this.email = employee.getEmail();
        this.role = "EMPLOYEE";
        this.password = employee.getEmail();
        this.enabled = this.password != null;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    public String getRole() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

