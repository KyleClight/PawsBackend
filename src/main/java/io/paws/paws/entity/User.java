package io.paws.paws.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(updatable = false, nullable = false)
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
