package com.realdb.finalproject.filter;

import com.realdb.finalproject.utility.JWTProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.realdb.finalproject.security.SecurityConstant.OPTIONS_HTTP_METHOD;
import static com.realdb.finalproject.security.SecurityConstant.TOKEN_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author jeremy on 2022/12/5
 */

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private JWTProvider jwtProvider;

    public JwtAuthorizationFilter(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // this method will fire every time a request comes in
        if (request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)) {
            response.setStatus(OK.value());
        } else {
            String authHeader = request.getHeader(AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
                 filterChain.doFilter(request, response);
                 return;
            }

            String token = authHeader.substring(TOKEN_PREFIX.length());
            String username = jwtProvider.getSubject(token);
            if (jwtProvider.isTokenValid(username, token)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                List<GrantedAuthority> authorities = jwtProvider.getAuthorities(token);
                Authentication authentication = jwtProvider.getAuthentication(username, authorities, request);
                // set authentication
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // clear the authentication
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}
