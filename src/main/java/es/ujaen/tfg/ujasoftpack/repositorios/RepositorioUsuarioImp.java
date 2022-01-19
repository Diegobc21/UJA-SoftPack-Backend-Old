/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.tfg.ujasoftpack.repositorios;

import es.ujaen.tfg.ujasoftpack.entidades.Usuario;
import es.ujaen.tfg.ujasoftpack.utilidades.MD5;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Diego
 */
@Repository
@Transactional
public class RepositorioUsuarioImp implements RepositorioUsuario {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Usuario> listar() {
        Query query;
        query = em.createQuery("SELECT e FROM Usuario e", Usuario.class);
        return query.getResultList();
    }
    
    @Override
    public List<Usuario> buscar(String nombre, long id){
        String Id = Long.toString(id);
        String name = nombre;
        Query query;
        query = em.createQuery("SELECT a FROM Usuario a WHERE a.id LIKE CONCAT('%', ?1, '%') AND a.nombre LIKE CONCAT('%', ?2, '%')", Usuario.class);
        query.setParameter(1, Id);
        query.setParameter(2, name);
        return query.getResultList();
    }

    @Override
    public List<Usuario> buscarIdLista(long id) {
        String Id = Long.toString(id);
        Query query;
        query = em.createQuery("SELECT a FROM Usuario a WHERE a.id LIKE CONCAT('%', ?1, '%')", Usuario.class);
        query.setParameter(1, Id);
        return query.getResultList();
    }
    
    @Override
    public Usuario buscarId(long id){
        return em.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> buscarNombre(String nombre) {
        if (!nombre.isEmpty()) {
            Query query;
            query = em.createQuery("SELECT a FROM Usuario a WHERE a.nombre LIKE CONCAT('%', ?1, '%')", Usuario.class);
            query.setParameter(1, nombre);
            return query.getResultList();
        } else {
            List<Usuario> allUsers = listar();
            return allUsers;
        }
    }
    
    @Override
    public Usuario buscarEmail(long id) {
        return Optional.ofNullable(em.find(Usuario.class, id)).get();
    }
    
    @Override
    public void editar(Usuario usuario, Usuario nuevo) {
        usuario.setNombre(nuevo.getNombre());
        usuario.setEmail(nuevo.getEmail());
        em.merge(usuario);
        flushAndClear();
    }

    @Override
    public void save(Usuario usuario) {
        String passMD5 = MD5.getMD5(usuario.getPassword());
        usuario.setPassword(passMD5);
        usuario.setActivo(false);
        em.persist(usuario);
        flushAndClear();
    }

    @Override
    public void delete(Usuario usuario) {
        em.remove(usuario);
        flushAndClear();
    }
    
    @Override
    public void flushAndClear() {
        em.flush();
        em.clear();
    }

}
