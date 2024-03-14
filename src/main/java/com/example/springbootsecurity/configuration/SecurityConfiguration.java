package com.example.springbootsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    //Cấu hình phân quyền
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> {
            authorize
                    //Tài ngueyen nào. quyền là gi
                    .requestMatchers("/home").permitAll() // permitAll ai cũng có thể vào được
                    .requestMatchers("/user").hasRole("USER")
                    .requestMatchers("/admin").hasRole("ADMIN")
            ;
        }).formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.builder().username("user").password(passwordEncoder().encode("123456")).roles("USER")
                .build();

        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }

}