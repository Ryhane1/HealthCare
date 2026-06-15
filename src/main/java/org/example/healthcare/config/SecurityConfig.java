package org.example.healthcare.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final org.example.healthcare.config.JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // public endpoints
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/favicon.ico").permitAll()
                        .requestMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                        // role protected endpoints
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/medecin/**").hasAnyRole("MEDECIN", "ADMIN")
                        .requestMatchers("/patient/**").hasAnyRole("PATIENT", "ADMIN")
                        .requestMatchers("/rendezvous/**").hasAnyRole("PATIENT", "MEDECIN", "ADMIN")
                        .requestMatchers("/dossiermedical/**").hasAnyRole("PATIENT", "MEDECIN", "ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                // ensure anonymous users get a 403 handled consistently by Spring
                .exceptionHandling(e -> e.authenticationEntryPoint(new org.springframework.security.web.authentication.Http403ForbiddenEntryPoint()))
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }





}