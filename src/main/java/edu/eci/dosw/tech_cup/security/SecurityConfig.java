package edu.eci.dosw.tech_cup.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.eci.dosw.tech_cup.services.UserService;

@Configuration
@EnableWebSecurity // Habilita la configuracion de seguridad web de Spring.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/h2-console/**", "/swagger-ui/**", "/swagger-ui.html", "/api-docs/**")
                        .permitAll()
                        .anyRequest().permitAll())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return userService::loadUserByEmail;
    }

    @Bean
    // AuthenticationManager procesa y valida credenciales durante el login.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    // PasswordEncoder define como se codifican/verifican passwords; BCryptPasswordEncoder aplica hash seguro.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
