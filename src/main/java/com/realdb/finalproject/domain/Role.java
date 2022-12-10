package com.realdb.finalproject.domain;

/**
 * @author jeremy on 2022/12/10
 */
public enum Role {
    ROLE_CUSTOMER("ROLE_CUSTOMER"),
    ROLE_EMPLOYEE("ROLE_EMPLOYEE");

    private final String text;

    Role(String role) {
        this.text = role;
    }

    public String getText() {
        return text;
    }
}
