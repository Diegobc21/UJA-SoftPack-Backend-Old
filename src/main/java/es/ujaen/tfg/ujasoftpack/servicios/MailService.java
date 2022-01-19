/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.tfg.ujasoftpack.servicios;

/**
 *
 * @author Diego
 */
public interface MailService {

    void emailRegistro(String to, String subject, String text);
}
