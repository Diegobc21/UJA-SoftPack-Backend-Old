/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.tfg.ujasoftpack.RESTcontrolador.DTO;

import es.ujaen.tfg.ujasoftpack.entidades.Usuario;

/**
 *
 * @author Diego
 */
public class DTOUsuario {
    
    long id;

    String nombre;

    String email;
    
    String password;
    
    public DTOUsuario(Usuario usuario){
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
    }
    
    public DTOUsuario(String nombre, String email, String password){
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
}
