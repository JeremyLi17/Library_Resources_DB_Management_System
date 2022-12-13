package com.realdb.finalproject.security;

import com.realdb.finalproject.filter.JwtAccessDeniedHandler;
import com.realdb.finalproject.filter.JwtAuthenticationEntryPoint;
import com.realdb.finalproject.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.realdb.finalproject.security.SecurityConstant.PUBLIC_URLS;
import static com.realdb.finalproject.security.SecurityConstant.UNAVAILABLE_URL_FOR_CUSTOMER;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * @author jeremy on 2022/12/10
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Configuration
    @Order(1)
    public static class CustomerSecurityConfig extends WebSecurityConfigurerAdapter {


        private final JwtAuthorizationFilter jwtAuthorizationFilter;
        private final JwtAccessDeniedHandler accessDeniedHandler;
        private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
        private final UserDetailsService userDetailsService;
        private final BCryptPasswordEncoder passwordEncoder;

        @Autowired
        public CustomerSecurityConfig(JwtAuthorizationFilter jwtAuthorizationFilter,
                                      JwtAccessDeniedHandler accessDeniedHandler,
                                      JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                                      @Qualifier("customerService")
                                                      UserDetailsService userDetailsService,
                                      BCryptPasswordEncoder passwordEncoder) {
            this.jwtAuthorizationFilter = jwtAuthorizationFilter;
            this.accessDeniedHandler = accessDeniedHandler;
            this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
            this.userDetailsService = userDetailsService;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        @Bean(name = "authManagerForCustomer")
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            // which userDetailService we are using
            // also pass a bitCrypto encoder
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().cors().and()
                    .sessionManagement().sessionCreationPolicy(STATELESS)
                    .and()
                    .authorizeRequests().antMatchers(PUBLIC_URLS).permitAll()
                    .antMatchers(UNAVAILABLE_URL_FOR_CUSTOMER).hasAnyAuthority("ROLE_EMPLOYEE")
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .and()
                    .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }

    @Configuration
    @Order(2)
    public static class EmployeeSecurityConfig extends WebSecurityConfigurerAdapter {

        private final JwtAuthorizationFilter jwtAuthorizationFilter;
        private final JwtAccessDeniedHandler accessDeniedHandler;
        private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
        private final UserDetailsService userDetailsService;
        private final BCryptPasswordEncoder passwordEncoder;

        @Autowired
        public EmployeeSecurityConfig(JwtAuthorizationFilter jwtAuthorizationFilter,
                                      JwtAccessDeniedHandler accessDeniedHandler,
                                      JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                                      @Qualifier("employeeService")
                                                      UserDetailsService userDetailsService,
                                      BCryptPasswordEncoder passwordEncoder) {
            this.jwtAuthorizationFilter = jwtAuthorizationFilter;
            this.accessDeniedHandler = accessDeniedHandler;
            this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
            this.userDetailsService = userDetailsService;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        @Bean(name = "authManagerForEmployee")
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            // which userDetailService we are using
            // also pass a bitCrypto encoder
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().cors().and()
                    .sessionManagement().sessionCreationPolicy(STATELESS)
                    .and()
                    .authorizeRequests().antMatchers(PUBLIC_URLS).permitAll()
                    .antMatchers(UNAVAILABLE_URL_FOR_CUSTOMER).hasAnyAuthority("ROLE_EMPLOYEE")
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .and()
                    .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }
}
