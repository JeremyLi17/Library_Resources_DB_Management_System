package com.realdb.finalproject.security;

/**
 * @author jeremy on 2022/12/5
 */
public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000; // 5 days in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";    // means this token is valid
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String ISSUER = "REAL DB LLC";
    public static final String ADMINISTRATION = "REAL DB LLC";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You don not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {
            "/api/customer/register",
            "/api/customer/login",
            "/api/customer/resetpassword/**",
            "/api/employee/register",
            "/api/employee/login",
            "/api/employee/resetpassword/**",
    };
}
