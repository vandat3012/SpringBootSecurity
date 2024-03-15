package com.example.springbootsecurity.repository;

import com.example.springbootsecurity.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRoleRepo extends JpaRepository<AppRole,Long> {
}
