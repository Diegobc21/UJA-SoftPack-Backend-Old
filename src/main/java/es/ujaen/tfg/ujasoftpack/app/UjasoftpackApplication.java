package es.ujaen.tfg.ujasoftpack.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Diego
 */
@SpringBootApplication(scanBasePackages = {
    "es.ujaen.tfg.ujasoftpack.servicios",
    "es.ujaen.tfg.ujasoftpack.repositorios",
    "es.ujaen.tfg.ujasoftpack.RESTcontrolador",
    "es.ujaen.tfg.ujasoftpack.seguridad"
})
@EntityScan(basePackages = "es.ujaen.tfg.ujasoftpack.entidades")
public class UjasoftpackApplication {

    public static void main(String[] args) {
        SpringApplication servidor = new SpringApplication(UjasoftpackApplication.class);
        ApplicationContext context = servidor.run(args);
    }

}
