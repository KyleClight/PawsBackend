package io.paws.paws.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String token;
    private int tel;
    private String name;
    private String email;
    private String password;
    private String imageUrl;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //Система ролей пользователей
        return List.of();
    }

    @Override
    public String getUsername() { //Имя пользователя, по которому идентифицирует система
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { //Не просрочен ли аккаунт
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //Не заблокирован ли аккаунт
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //Не истёк ли срок действия пароля
        return true;
    }

    @Override
    public boolean isEnabled() { //Активирован ли пользователь
        return true;
    }
}
