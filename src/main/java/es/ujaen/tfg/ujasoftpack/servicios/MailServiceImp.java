/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.tfg.ujasoftpack.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego
 */
@Service
public class MailServiceImp implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void emailRegistro(String to, String subject, String text) {        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        javaMailSender.send(message);
    }
}
