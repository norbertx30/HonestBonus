package com.github.groupproject.email;

public interface EmailService {
    void sendMessage(String to,String subject,String text);
}
