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
    public static final String[] UNAVAILABLE_URL_FOR_CUSTOMER = {
            "/api/customer/list",
            "/api/customer/find/**",
            "/api/customer/delete",
            // employee
            "/api/employee/**",
            // author
            "/api/author/add",
            "/api/author/delete",
            "/api/author/update",
            // book
            "/api/book/add",
            "/api/book/delete",
            "/api/book/update",
            // copy
            "/api/copy/add",
            "/api/copy/delete",
            // event
            "/api/event/add/**",
            "/api/event/delete/**",
            "/api/event/update",
            // sponsor
            "/api/sponsor/add/**",
            "/api/sponsor/add/delete",
            // study room
            "/api/studyroom/add",
            "/api/studyroom/update",
            "/api/studyroom/delete",
            // authorSeminar
            "/api/authorSeminar/add",
            "/api/authorSeminar/delete",
            // bookAuthor
            "/api/bookAuthor/add",
            "/api/bookAuthor/delete",
            // customerExhibition -> none
            // seminarSponsor
            "/api/seminarSponsor/add",
            "/api/seminarSponsor/delete"
    };
}
