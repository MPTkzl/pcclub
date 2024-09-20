package com.shop.pc_club.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class ModelUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    @Column(name = "name", nullable = false)
    private String name;

    @Email(message = "Некорректный email")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Size(min = 8, message = "Пароль должен быть не менее 8 символов")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active")
    private boolean active;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Auth auth;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<RoleEnum> getRoles() {
        Set<RoleEnum> roles = new HashSet<>();
        if (auth != null && auth.getRole() != null) {
            String[] roleStrings = auth.getRole().split(",");
            for (String roleString : roleStrings) {
                try {
                    roles.add(RoleEnum.valueOf(roleString.trim().toUpperCase()));
                } catch (IllegalArgumentException e) {
                    System.out.println("Некорректное значение роли: " + roleString);
                }
            }
        }
        return roles;
    }

    public void setRoles(Set<RoleEnum> roles) {
        if (auth == null) {
            auth = new Auth();
        }
        auth.setRole(String.join(",", roles.stream()
                .map(RoleEnum::name)
                .toArray(String[]::new)));
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}
