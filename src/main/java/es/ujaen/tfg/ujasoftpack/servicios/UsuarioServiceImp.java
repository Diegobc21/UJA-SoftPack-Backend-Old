/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.tfg.ujasoftpack.servicios;

import es.ujaen.tfg.ujasoftpack.entidades.Usuario;
import es.ujaen.tfg.ujasoftpack.repositorios.RepositorioUsuario;
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
    public Usuario buscarEmail(long id) {
        return repositorio.buscarEmail(id);
    }
    
    @Override
    public void add(Usuario usuario) {
        repositorio.save(usuario);
    }

    @Override
    public void editar(Usuario usuario, Usuario nuevo) {
        repositorio.editar(usuario, nuevo);
    }

    @Override
    public void delete(Usuario usuario) {
        repositorio.delete(usuario);
    }

}