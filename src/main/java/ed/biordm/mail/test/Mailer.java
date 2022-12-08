/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.biordm.mail.test;

import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author tzielins
 */
@Service
public class Mailer {
    
    final MailSender mailSender;
    final String FROM = "biodare@ed.ac.uk";
    
    final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    public Mailer() throws MessagingException, GeneralSecurityException {
        JavaMailSenderImpl s = new JavaMailSenderImpl();
        s.setSession(makeSession());
        
        //s.testConnection();
        this.mailSender = s;
    }
    
    public boolean send(String to,String subject,String body) {
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(FROM);    
        
        
        try {
            mailSender.send(message);
            log.info("MAIL SENT\t{}\t{}",to,subject);
            return true;
        } catch (MailException e) {
            log.error("Cannot send mail to: {}; {}",to,e.getMessage(),e);
            return false;
        }
        
    }
    
    protected Session makeSession() {

        String host = "localhost";
        String userName = "biodare";
        String password = "";

        Properties prop = new Properties();
        prop.put("mail.smtp.host",host);
        prop.put("mail.from",FROM);
        prop.put("mail.user", userName);
        //prop.put("mail.smtp.auth",true);
        //prop.put("mail.smtp.starttls.enable",true);

        //Authenticator authenticator = new SimpleMailAuthenticator(userName, password);

        //return Session.getInstance(prop, authenticator);
        return Session.getInstance(prop);
    }
        
    static class SimpleMailAuthenticator extends Authenticator {


        final String userName;
        final PasswordAuthentication authentication;

        public SimpleMailAuthenticator(String userName,String password) {
                super();
                this.userName = userName;
                authentication = new PasswordAuthentication(userName, password);
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
                return authentication;
        }


    }        
    
}
