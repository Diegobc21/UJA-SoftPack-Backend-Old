/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.tfg.ujasoftpack.servicios;

import es.ujaen.tfg.ujasoftpack.entidades.Usuario;
import es.ujaen.tfg.ujasoftpack.excepciones.EmailDuplicado;
import es.ujaen.tfg.ujasoftpack.repositorios.RepositorioUsuario;
import es.ujaen.tfg.ujasoftpack.utilidades.MD5;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego
 */
@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    RepositorioUsuario repositorio;

    @Override
    public List<Usuario> listar() {
        return repositorio.listar();
    }
    
    @Override
    public List<Usuario> buscar(String nombre, long id){
        return repositorio.buscar(nombre, id);
    }

    @Override
    public List<Usuario> buscarIdLista(long id) {
        return repositorio.buscarIdLista(id);
    }
    
    @Override
    public Usuario buscarId(long id){
        return repositorio.buscarId(id);
    }
    
    @Override
    public List<Usuario> buscarNombre(String nombre) {
        return repositorio.buscarNombre(nombre);
    }
    
    @Override
    public Usuario getEmail(long id) {
        return repositorio.getEmail(id);
    }
    
    @Override
    public Usuario buscarEmail(String email) {
        return repositorio.buscarEmail(email);
    }
    
    @Override
    public void add(Usuario usuario) {
        usuario.setPassword(MD5.getMD5(usuario.getPassword()));
        usuario.setOnline(false);
        
        try {
            repositorio.save(usuario);
        } catch (Exception e) {
            throw new EmailDuplicado();
        }
    }

    @Override
    public void editar(Usuario usuario, Usuario nuevo) {
        usuario.setNombre(nuevo.getNombre());
        usuario.setEmail(nuevo.getEmail());
        
        repositorio.editar(usuario, nuevo);
    }

    @Override
    public void delete(Usuario usuario) {
        repositorio.delete(usuario);
    }

}