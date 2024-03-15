package com.example.springbootsecurity.repository;

import com.example.springbootsecurity.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByName(String name);
}
