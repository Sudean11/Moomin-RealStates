package com.academicproject.moomin.realstates.config;



import com.academicproject.moomin.realstates.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    String[] roles = {"CLIENT","ADMIN"}; // You can make this a call from the DB
//  String [] roles = {"CLIENT"}; // Try this :)

    @Bean
    public UserDetailsService userDetailsSvc() {
        return userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(new Customizer<AuthorizeHttpRequestsConfigurer<org.springframework.security.config.annotation.web.builders.HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>() {
                    @Override
                    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry) {
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll();
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/v1/property/elastic").permitAll();
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/v1/authenticate").permitAll();
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/v1/users/{userId}/approve").hasRole("ADMIN");
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/v1/offer").hasAnyRole("CUSTOMER", "OWNER");
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/v1/users?unverified=true").hasRole("ADMIN");
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/v1/property/user").hasRole("OWNER");
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/v1/property/:id/delete").hasAnyRole("OWNER","ADMIN");
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll();
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/v1/**").permitAll();

                    }
                })
        ;
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsSvc());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
