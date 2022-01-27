/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.tfg.ujasoftpack.servicios;

import es.ujaen.tfg.ujasoftpack.entidades.Usuario;
import java.util.List;

/**
 *
 * @author Diego
 */

public interface UsuarioService {
    
    List<Usuario> listar();
    
    List<Usuario> buscar(String nombre, long id);
    
    List<Usuario> buscarIdLista(long id);
    
    Usuario buscarId(long id);
    
    List<Usuario> buscarNombre(String nombre);
    
    Usuario getEmail(long id);
    
    Usuario buscarEmail(String email);
    
    void add(Usuario usuario);
    
    void editar(Usuario usuario, Usuario nuevo);
    
    void delete(Usuario usuario);
}
