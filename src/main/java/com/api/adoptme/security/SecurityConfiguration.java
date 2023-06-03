package com.api.adoptme.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private MyUserDetailsService myUserDetailsService;

    @Autowired
    @Lazy
    public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    /**
     * Creates a bean for JwtRequestFilter.
     *
     * @return JwtRequestFilter instance.
     */
    @Bean
    public JwtRequestFilter authJwtRequestFilter() {
        return new JwtRequestFilter();
    }
    /**
     * Creates a bean for BCryptPasswordEncoder.
     *
     * @return BCryptPasswordEncoder instance.
     */
    // register user
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * Configures the Security Filter Chain.
     *
     * @param http HttpSecurity instance.
     * @return SecurityFilterChain instance.
     * @throws Exception if there is an error during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers(
                        "/auth/users/register/", "/auth/users/login/","/api/animal/"
                ).permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
        http.addFilterBefore(authJwtRequestFilter(), UsernamePasswordAuthenticationFilter.class); // added for JWT login
        return http.build();
    }
    /**
     * Creates a bean for AuthenticationManager.
     *
     * @param authConfig AuthenticationConfiguration instance.
     * @return AuthenticationManager instance.
     * @throws Exception if there is an error during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    /**
     * Creates a bean for DaoAuthenticationProvider.
     *
     * @return DaoAuthenticationProvider instance.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    /**
     * Creates a bean for MyAuthorDetails.
     *
     * @return MyAuthorDetails instance.
     */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MyUserDetails myAuthorDetails() {
        return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
