package com.arib.NotesAppApi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "User")
public class User implements UserDetails {

    public User(@NotBlank(message = "Email is required") String email,
                @NotBlank(message = "Password cannot be blank") String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public User(int id, @NotBlank(message = "First name is required") String firstName, String lastName,
                @NotBlank(message = "Email is required") String email) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @NotBlank(message = "First name is required")
    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email Address is Not Valid")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
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
        return true;
    }
}