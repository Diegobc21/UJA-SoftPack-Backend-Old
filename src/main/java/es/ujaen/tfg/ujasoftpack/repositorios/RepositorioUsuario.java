/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.tfg.ujasoftpack.repositorios;

import es.ujaen.tfg.ujasoftpack.entidades.Usuario;
import java.util.List;
import org.springframework.data.repository.Repository;

/**
 *
 * @author Diego
 */
public interface RepositorioUsuario extends Repository<Usuario, Integer> {

    List<Usuario> listar();
    
    List<Usuario> buscar(String nombre, long id);

    List<Usuario> buscarIdLista(long id);

    Usuario buscarId(long id);
    
    Usuario buscarEmail(String email);

    List<Usuario> buscarNombre(String nombre);

    Usuario getEmail(long id);

    void editar(Usuario usuario, Usuario nuevo);

    void save(Usuario usuario);

    void delete(Usuario usuario);

    void flushAndClear();

}
