package com.nhp.itsocialserver.services;

public interface EmailService {
    void sendTextEmail(String to, String subject, String text);
}
