package com.realdb.finalproject.exception.domain;

/**
 * @author jeremy on 2022/12/8
 */
public class EmailExistException extends Exception{
    public EmailExistException(String message) {
        super(message);
    }
}
