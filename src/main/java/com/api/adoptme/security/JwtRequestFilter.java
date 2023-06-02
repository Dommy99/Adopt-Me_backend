package com.api.adoptme.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class JwtRequestFilter extends OncePerRequestFilter {
    Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JWTUtils jwtUtils;
    /**
     * This method extracts JWT token from the request header and returns the token.
     * @param request Incoming HttpServletRequest object
     * @return JWT token string
     */
    // "Authorization" : "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsZW9AYW9sLmNvbSIsImlhdCI6MTY4MjQ1MjU4NywiZXhwIjoxNjgyNTM4OTg3fQ.GtsCdU8VV8MFzTLPuXsQmbs6Nnovbdax0fbU8QDH04U"
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasLength(headerAuth) && headerAuth.startsWith("Bearer")) {

            // so we return JWT key
            // eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsZW9AYW9sLmNvbSIsImlhdCI6MTY4MjQ1MjU4NywiZXhwIjoxNjgyNTM4OTg3fQ.GtsCdU8VV8MFzTLPuXsQmbs6Nnovbdax0fbU8QDH04U
            return headerAuth.substring(7);
        }
        return null;
    }
    /**
     * This method is responsible for authenticating the user by extracting the JWT token from the request header,
     * validating it, and setting the user details in the SecurityContext if it is a valid token.
     * @param request Incoming HttpServletRequest object
     * @param response Outgoing HttpServletResponse object
     * @param filterChain FilterChain object
     * @throws ServletException If an exception occurred that interferes with the filter's normal operation
     * @throws IOException If an input or output exception occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            // check if the jwt key is valid and not null
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                // if valid get user email from the key
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                // load user details from the key
                UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
                // set username and password authentication token from user user details
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                // build request and get security content
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.info("Cannot set user authentication");
        }
        filterChain.doFilter(request, response);
    }
}
