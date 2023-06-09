package com.codeup.codeupspringblog.config;

import com.codeup.codeupspringblog.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;


// Informs spring that this class is to configure the Spring application
@Configuration
// Will allow us to edit the MVC security for our application
@EnableWebSecurity
public class SecurityConfiguration {
    // Dependency that we inject, so that we can retrieve details about the user who is trying to log in.
    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }


    // The @Bean annotation means that the class itself is being managed by Spring.

    // Is a class that is managed by Spring, specifically to hash and unhash our User passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // This class is used to manage the users Authentication status.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // This class will provide filters for our Spring security for different URL mappings.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                        /* Pages that require authentication
                         * only authenticated users can create and edit ads */
                        .requestMatchers("/ads/create", "/ads/*/edit").authenticated()
                        /* Pages that do not require authentication
                         * anyone can visit the home page, register, login, and view ads */
                        .requestMatchers("/", "/parks", "/ads/*", "/sign-up", "/login").permitAll()
                        // allow loading of static resources
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                )
                /* Login configuration */
                .formLogin((login) -> login.loginPage("/login").defaultSuccessUrl("/parks"))
                /* Logout configuration */
                .logout((logout) -> logout.logoutSuccessUrl("/login?logout"))
                .httpBasic(withDefaults());
        return http.build();
    }
}