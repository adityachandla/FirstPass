package com.firstpass.services;

public interface PasswordService {
    String getPassword(String userName, String appName);
    String getPassword(String appName);
}
