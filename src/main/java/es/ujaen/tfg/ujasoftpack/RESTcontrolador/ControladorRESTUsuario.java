package es.ujaen.tfg.ujasoftpack.RESTcontrolador;

import es.ujaen.tfg.ujasoftpack.entidades.Usuario;
import es.ujaen.tfg.ujasoftpack.servicios.MailServiceImp;
import es.ujaen.tfg.ujasoftpack.servicios.UsuarioServiceImp;
import java.util.List;
import javax.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diego
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Validated
@RequestMapping("/ujasoftpack")
public class ControladorRESTUsuario {

    @Autowired
    UsuarioServiceImp servicioUsuario;

    @Autowired
    MailServiceImp servicioEmail;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    void comprobarConexion() {
    }

    /*
     * CREAR USUARIO
     */
    @PostMapping("/usuarios")
    ResponseEntity crear(@RequestBody Usuario usuario) {

        try {
            servicioUsuario.add(usuario);
//            emailRegistro(usuario.getId());
        } catch (Exception e) {
        }

        return ResponseEntity.ok().build();
    }

    /*
     * LISTAR TODOS LOS USUARIOS, LISTAR POR NOMBRE, LISTAR POR ID 
     */
    @GetMapping("/usuarios")
    ResponseEntity<List<Usuario>> buscarNombreLista(@RequestParam(value = "nombre", required = false) String nombre, @RequestParam(value = "id", required = false) String id) {
        if (nombre == null || id == null) {
            if (nombre != null) {
                return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.buscarNombre(nombre));
            } else if (id != null) {
                try {
                    return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.buscarIdLista(Long.parseLong(id)));
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.buscar(nombre, Long.parseLong(id)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.listar());
    }
    
    /*
     * BUSCAR USUARIO POR EMAIL
     */
    @GetMapping("/usuarios/")
    ResponseEntity<Usuario> buscarEmail(@Email @RequestParam(value = "email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.buscarEmail(email));
    }

    /*
     * BUSCAR EMAIL POR ID
     */
    @GetMapping("/usuarios/{id}/email")
    ResponseEntity<String> getEmail(@PathVariable("id") String id) {
        String email = servicioUsuario.buscarId(Long.parseLong(id)).getEmail();
        if (email == null) {
            return null;
        }
        return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.buscarId(Long.parseLong(id)).getEmail());
    }

    /*
     * BUSCAR USUARIO POR ID
     */
    @GetMapping("/usuarios/{id}")
    ResponseEntity<Usuario> buscarId(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(servicioUsuario.buscarId(id));
    }

    /*
     * EDITAR NOMBRE
     */
    @PutMapping("/usuarios/{id}")
    ResponseEntity editar(@PathVariable("id") long id, @RequestBody Usuario usuario) {
        Usuario u = servicioUsuario.buscarId(id);

        try {
            servicioUsuario.editar(u, usuario);
        } catch (Exception e) {
        }

        return ResponseEntity.ok().build();
    }

    /*
     * ELIMINAR USUARIO
     */
    @DeleteMapping("/usuarios/{id}")
    ResponseEntity eliminar(@PathVariable("id") long id) {
        Usuario usuario = servicioUsuario.buscarId(id);

        try {
            servicioUsuario.delete(usuario);
        } catch (Exception e) {
        }

        return ResponseEntity.ok().build();
    }

    /**
     * EMAIL AUTOMÁTICO
     */
    @GetMapping("usuarios/{id}/email-registro")
    ResponseEntity emailRegistro(@PathVariable("id") long id) {
        Usuario u = servicioUsuario.buscarId(id);
        String subject = "Registro con éxito";
        String text = "Gracias por crear tu cuenta en UJA-SoftPack.";

        try {
            servicioEmail.emailRegistro(u.getEmail(), subject, text);
        } catch (Exception e) {
        }

        return ResponseEntity.ok().build();
    }

}
