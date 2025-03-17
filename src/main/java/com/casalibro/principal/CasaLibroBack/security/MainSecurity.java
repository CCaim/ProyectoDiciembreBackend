package com.casalibro.principal.CasaLibroBack.security;

import com.casalibro.principal.CasaLibroBack.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF (opcional)
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/auth/**", "/libro/getAll", "/libro/{id}", "/v2/api-docs/**", "/configuration/**").permitAll()
                        .anyRequest().authenticated() // Cualquier otra ruta requiere autenticación
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página de login personalizada (opcional)
                        .permitAll() // Permite acceso sin autenticación a la página de login
                )
                .logout(logout -> logout
                        .permitAll() // Permite que cualquier usuario pueda cerrar sesión
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Usamos sesiones tradicionales
                        .maximumSessions(1) // Limitar el número de sesiones concurrentes (opcional)
                        .expiredUrl("/login?expired=true") // URL de redirección después de sesión expirada
                );

        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
