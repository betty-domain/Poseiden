package com.poseiden.springboot.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Collections;

/**
 * User entity
 */
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "FullName is mandatory")
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    private String role;

    /**
     * Constructor without properties initialization
     */
    public User()
    {

    }

    /**
     * Constructor with properties initialization
     * @param username username
     * @param password password
     * @param fullname fullname
     * @param role role
     */
    public User(String username, String password, String fullname, String role)
    {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    /**
     * check if account is not Expired
     * @return true if not expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * check if account is not expired
     * @return true if not expired
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * check if credentials are not expired
     * @return ture if not expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * check if user is enabled
     * @return true if enabled
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Get list of grantedAuthorities (user rules)
     * @return list of user's granted authorites
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }
}
