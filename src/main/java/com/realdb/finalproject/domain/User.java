package com.realdb.finalproject.domain;

import java.io.Serializable;

/**
 * @author jeremy on 2022/12/10
 */
public abstract class User implements Serializable {
    public abstract void setEmail(String email);
    public abstract String getEmail();
    public abstract void setPassword(String password);
    public abstract String getPassword();
    public abstract void setUsername(String username);
    public abstract String getUsername();
    public abstract void setNotLocked(boolean notLocked);
    public abstract boolean isNotLocked();
    public abstract boolean isActive();
    public abstract void setActive(boolean active);
    public abstract String[] getAuthorities();
}
