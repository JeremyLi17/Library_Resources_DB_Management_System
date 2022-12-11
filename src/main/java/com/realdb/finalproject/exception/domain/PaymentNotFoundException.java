package com.realdb.finalproject.exception.domain;

/**
 * @author jeremy on 2022/12/11
 */
public class PaymentNotFoundException extends Exception{
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
