package com.realdb.finalproject.exception.domain;

/**
 * @author jeremy on 2022/12/11
 */
public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
