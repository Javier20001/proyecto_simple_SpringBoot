package mx.com.gm.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import mx.com.gm.validation.impl.ValidarCaracterEspecialesImpl;

@Constraint(validatedBy = ValidarCaracterEspecialesImpl.class)//la futura clase que contendra la logica de la validacion(ya que esta es la interfaz)
@Target({ElementType.METHOD, ElementType.FIELD})//donde podria usarse nuestra validacion, ya sean methodos o campos 
@Retention(RetentionPolicy.RUNTIME)//esto indica que se va a chequear en tiempo de ejecucion 
public @interface ValidarCaracterEspeciales {

    //definir mensaje de error por defecto
    public String message() default "EL nombre no es corecto falta un _";
    //definir los grupos: esto sirve para que en tiempo de ejecucion se vaya chequeando la validacion antes de enviar los datos
    //siempre y cuando aclaremos cuales los grupos, ej: grp1->input nombre , input apellido / grp2->input edad , input mail
    Class<?>[] groups() default {};
    //definir los payload esto se utiliza para los metadatos 
    Class<?extends Payload> [] payload() default {};   
}
