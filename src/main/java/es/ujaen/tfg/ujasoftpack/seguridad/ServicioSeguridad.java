package es.ujaen.tfg.ujasoftpack.seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego
 */
@Service
@Configuration
@EnableWebSecurity
public class ServicioSeguridad extends WebSecurityConfigurerAdapter {

    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").roles("ADMIN").password("{noop}admin");
        auth.inMemoryAuthentication()
                .withUser("usuario").roles("USUARIO").password("{noop}password");
    }

    /**
     *
     * @param httpSecurity
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        httpSecurity.httpBasic();

        /* Aqui comprobamos quienes son los usuarios que tienen acceso a nuestro servidor */
        
//        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET,"/ujasoftpack/")
//                .hasAnyRole("ADMIN","USUARIO");
//        
//        httpSecurity.authorizeRequests().antMatchers("/ujasoftpack/usuarios")
//                .hasRole("ADMIN");
//        
//        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET,"/ujasoftpack/usuarios/{id}")
//                .hasAnyRole("ADMIN");
//        
//        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET,"/ujasoftpack/usuarios/{id}/email")
//                .hasAnyRole("ADMIN","USUARIO");
//        
//        httpSecurity.authorizeRequests().antMatchers(HttpMethod.PUT, "/ujasoftpack/usuarios/{id}")
//                .hasRole("ADMIN");
//
//        httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/ujasoftpack/usuarios")
//                .hasRole("ADMIN");
//        
//        httpSecurity.authorizeRequests().antMatchers(HttpMethod.DELETE, "/ujasoftpack/usuarios/{id}")
//                .hasRole("ADMIN");
    }
}
