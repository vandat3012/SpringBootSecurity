package com.example.springbootsecurity.service;

import com.example.springbootsecurity.model.AppUser;
import com.example.springbootsecurity.repository.IAppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements IAppUserService, UserDetailsService {
    @Autowired
    private IAppUserRepo iAppUserRepo;
    @Override
    public Iterable<AppUser> findAll() {
        return iAppUserRepo.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return iAppUserRepo.findById(id);
    }

    @Override
    public AppUser save(AppUser user) {
        return iAppUserRepo.save(user);
    }

    @Override
    public void remove(Long id) {
        iAppUserRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = iAppUserRepo.findByName(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(user.getAppRole());

        UserDetails userDetails = new User(
                user.getName(),
                user.getPassword(),
                grantedAuthorities
        );

        return userDetails;
    }
}
