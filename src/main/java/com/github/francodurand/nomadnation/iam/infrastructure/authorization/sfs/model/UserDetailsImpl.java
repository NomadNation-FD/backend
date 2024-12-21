package com.github.francodurand.nomadnation.iam.infrastructure.authorization.sfs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.francodurand.nomadnation.iam.domain.model.aggregates.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for providing the user details to the Spring
 * Security framework.
 * It implements the UserDetails interface.
 */
@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

    private final String username;
    @JsonIgnore
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String email, String password) {
        this.username = email;
        this.password = password;
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    /**
     * This method is responsible for building the UserDetailsImpl object from the
     * User object.
     * 
     * @param user The user object.
     * @return The UserDetailsImpl object.
     */
    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getEmail(),
                user.getPassword());
    }
}