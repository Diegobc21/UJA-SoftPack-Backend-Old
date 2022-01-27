/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.tfg.ujasoftpack.entidades;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Diego
 */

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @NotNull
    @Column(unique = true, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "nombre")
    private String nombre;
    
    @Email
    @NotNull
    
    @Column(unique = true, name = "email")
    private String email;
    
    @NotNull
    @Column(name = "password")
    private String password;
    
    @Column(name = "online")
    private boolean online;
    
//    @NotNull
//    @Column(name = "activo")
//    private boolean activo;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.online = false;
//        this.activo = false;
    }
    
    public Usuario(String nombre, String email, String pass) {
        this.nombre = nombre;
        this.email = email;
        this.password = pass;
//        this.activo = false;
        this.online = false;
    }

    public Usuario() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

//    public boolean isActivo() {
//        return activo;
//    }

//    public void setActivo(boolean activo) {
//        this.activo = activo;
//    }
    
}
