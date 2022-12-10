package com.realdb.finalproject.exception.domain;

/**
 * @author jeremy on 2022/12/8
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
