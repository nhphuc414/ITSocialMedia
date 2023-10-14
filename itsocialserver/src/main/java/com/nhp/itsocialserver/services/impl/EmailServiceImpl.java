package com.nhp.itsocialserver.services.impl;

import com.nhp.itsocialserver.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendTextEmail(String to, String subject, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom("nhphuc414@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void sendSimpleMessage(String to, String subject, String text) {
        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setText(text);
            message.setSubject(subject);
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Start sending");
                    javaMailSender.send(message);
                    System.out.println("End sending");
                }
            });
            th.start();
        } catch (MailException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    @Async
    public void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template, String templateArgs) {

    }

    @Override
    @Async
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {

    }
}
