package fr.eni.tp.filmotheque.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/avis").authenticated()
                        .requestMatchers("/ajouterFilm").hasRole("admin")
                        .requestMatchers("/ajouterPersonne/**").hasRole("admin")
                        .requestMatchers("/**").permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .logout((logout) -> logout.logoutSuccessUrl("/"));
        return http.build();
    }

    @Bean // on définit un bean pour l'utilitaire d'encryption de mot de passe
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

