package com.casalibro.principal.CasaLibroBack.security;

import com.casalibro.principal.CasaLibroBack.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
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
                // Desactiva CSRF si no estás usando tokens para proteger formularios
                .csrf(csrf -> csrf.disable())

                // Configuración de las rutas públicas y protegidas
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll() // Permitir login y registro
                        .requestMatchers("/libro/getAll", "/libro/{id}").permitAll() // Rutas públicas para libros
                        .anyRequest().authenticated() // Proteger todas las demás rutas
                )

                // Configuración de login
                .formLogin(form -> form
                        .loginPage("/auth/login") // Página de login
                        .defaultSuccessUrl("/home", true) // Redirigir tras login exitoso
                        .permitAll()
                )

                // Configuración de logout
                .logout(logout -> logout
                        .logoutUrl("/auth/logout") // Endpoint para cerrar sesión
                        .logoutSuccessUrl("/auth/login?logout=true") // Redirigir tras logout
                        .permitAll()
                )

                // Configuración de manejo de sesiones
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Crear sesión si es necesario
                )

                // Configuración de CORS (si el frontend está en un dominio diferente)
                .cors(cors -> cors.configurationSource(request -> new org.springframework.web.cors.CorsConfiguration().applyPermitDefaultValues()));

        return http.build();
    }
}