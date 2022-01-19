/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.tfg.ujasoftpack.repositorios;

import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author Diego
 */
public interface RepositorioEmail {
    public SimpleMailMessage templateSimpleMessage();
}
