package com.todo.crud.rest2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    @Enumerated
    private Role role;


    //Este metodo retorna una lista de roles, en este caso devolvera solo uno USER o ADMIN por eso
    //se hace un new SimpleGrantedAuthority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    //Metodo para obtener el username, si no tuviera username y se utilizaria email entonces retorne el email
    @Override
    public String getUsername() {
        return username;
    }


    //IMPORTANTE, por defecto vienen todos false y hay q cambiarlos a true
    //Verifica si la cuenta expiró
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //verifica que la cuenta no esté bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //verifica que las credenciales no expiraron
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
