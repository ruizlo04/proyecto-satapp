package com.example.proyecto_satapp_Carlos_Rafa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}1234")
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csfr -> csfr.disable());
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers(HttpMethod.POST, "/alumno").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/tecnico").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/categoria").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/incidencia").hasAnyRole()
                .requestMatchers(HttpMethod.POST,"/equipo").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/incidencia").hasAnyRole()
                .requestMatchers(HttpMethod.POST,"/personal").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/ubicacion").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/alumno").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/tecnico").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/categoria").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/ubicacion").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/equipo").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/personal").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/tecnico").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/incidencia").hasAnyRole()
                .requestMatchers(HttpMethod.DELETE, "/categoria").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/alumno").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/tecnico").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/equipo").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/personal").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/ubicacion").hasRole("ADMIN")
                .anyRequest().authenticated());
        return http.build();
    }
}
