package com.example.springbootsecurity.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor
@Data
@Table(name = "role")
public class AppRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
