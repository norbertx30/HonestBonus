package com.github.groupproject.email.impl;

import com.github.groupproject.email.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    private JavaMailSender emailSender;


    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendMessage(String to, String subject, String uuid) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(to);
        mail.setFrom("javovsky9@o2.pl");
        mail.setSubject(subject);

        String url = "http://localhost:8080/set_password/" + uuid;
        String message = "Your uuid is: " + uuid + "\n" +
                "You can set your password here: " + url;
        mail.setText(message);
        try {
            emailSender.send(mail);
            LOG.info("Email has been sent to: " + to);
        } catch (MailException e) {
            LOG.info("Error" + e.getMessage());
        }
    }


}
