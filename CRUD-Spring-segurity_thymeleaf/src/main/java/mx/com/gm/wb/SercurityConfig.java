package mx.com.gm.wb;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SercurityConfig extends WebSecurityConfigurerAdapter{


    //agregar usuarios se lo conoce como AUtenticasion
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()// con esto agregamos usarios en memoria, agregando sus credenciales
            .withUser("admin")//asignamos el usario
                .password("{noop}123")//contraseña
                .roles("ADMIN","USER")//y roles
            .and()//clasula para agregar otro usuario 
            .withUser("user")
                .password("{noop}123")
                .roles("USER")
            ;
    }

    @Override
    //agrgar autorizaciones se lo conoce como autorizacion 
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()//clausula para agregar autorizaciones
            .antMatchers("/editar/**" ,  "/agregar/**" , "/eliminar", "/traer/**")//indicamos que se va poder hacer 
                .hasRole("ADMIN")//y solo quien lo va hacer
            .antMatchers("/","/traer/**")//indicamos el inicio 
                .hasAnyRole("USER","ADMIN")//y que solo va a poder verlo el user y el admin
            .and()
                .formLogin()//aññadimos un formulario d elogin
                .loginPage("/login")//y donde va estar
            .and()
                .exceptionHandling().accessDeniedPage("/errores/403")//y una pagina de error por si hay problemas
            ;
    }
    
}
