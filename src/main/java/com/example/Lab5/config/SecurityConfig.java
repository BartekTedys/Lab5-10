package com.example.Lab5.config;

import com.example.Lab5.services.MyUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) ->
                        auth.requestMatchers(HttpMethod.POST, "/graphql/**").permitAll()
                                .requestMatchers(HttpMethod.POST).hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.PUT).hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.PATCH).hasAnyRole("ADMIN", "USER")
                                .requestMatchers(PathRequest.toH2Console()).permitAll() // Allow H2 console access
                                .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN") // Restrict DELETE requests to /api/** to ADMIN role
                                .requestMatchers(HttpMethod.GET).permitAll() // Allow all GET requests
                                .anyRequest().authenticated() // Allow all other requests
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // Only allows content in frames from the same origin (no embedded content) (for H2 console)
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF protection (handle in production)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Set session management to stateless
                .cors(Customizer.withDefaults()) // Enable CORS with default configuration
                .httpBasic(Customizer.withDefaults()) // Enable HTTP Basic authentication with default settings
                .formLogin(Customizer.withDefaults()); // Enable form-based login with default settings
        return http.build();
    }

//    @Bean
//    UserDetailsSercvce
}


//
//@Configuration
//@EnableMethodSecurity(securedEnabled = true)
//public class SecurityConfig {
//
//    private final MyUserDetailsService myUserDetailsService; // Custom UserDetailsService
//
//    // Constructor injection
//    public SecurityConfig(MyUserDetailsService myUserDetailsService) {
//        this.myUserDetailsService = myUserDetailsService;
//    }
//
//    // Password encoder bean for encrypting passwords
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // Configure HTTP security
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((auth) ->
//                        auth.requestMatchers(HttpMethod.GET, "/graphql").permitAll() // Allow unauthenticated access to GraphQL GET
//                                .requestMatchers(HttpMethod.POST, "/graphql/**").permitAll() // Allow unauthenticated access to GraphQL POST
//                                .requestMatchers(HttpMethod.GET, "/pets/**", "/households/**").hasAnyRole("USER", "ADMIN") // Only users with appropriate roles can access GET for pets/households
//                                .requestMatchers(HttpMethod.POST, "/pets/**", "/households/**").hasRole("ADMIN") // Only ADMIN can POST pets/households
//                                .requestMatchers(HttpMethod.PUT, "/pets/**", "/households/**").hasAnyRole("USER", "ADMIN") // Users and ADMIN can PUT
//                                .requestMatchers(HttpMethod.PATCH, "/pets/**", "/households/**").hasAnyRole("USER", "ADMIN") // Users and ADMIN can PATCH
//                                .requestMatchers(HttpMethod.DELETE, "/pets/**", "/households/**").hasRole("ADMIN") // Only ADMIN can DELETE pets/households
//                                .requestMatchers(PathRequest.toH2Console()).permitAll() // Allow H2 console access
//                                .anyRequest().authenticated() // All other requests require authentication
//                )
//                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // Only allows content in frames from the same origin (for H2 console)
//                .csrf(csrf -> csrf.disable())  // Disable CSRF protection (for development purposes, enable it for production)
//                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session management
//                .cors(cors -> cors.disable()) // Disable CORS for simplicity (Enable in production)
//                .httpBasic(Customizer.withDefaults()) // Enable HTTP Basic authentication with default settings
//                .formLogin(Customizer.withDefaults()); // Enable form-based login with default settings
//
//        return http.build();
//    }
//
//    // DaoAuthenticationProvider bean to link with UserDetailsService
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(myUserDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//}
