package com.realdb.finalproject.exception.domain;

/**
 * @author jeremy on 2022/12/11
 */
public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message) {
        super(message);
    }
}
