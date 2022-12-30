package mx.com.gm.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import lombok.Data;
import mx.com.gm.validation.ValidarCaracterEspeciales;

@Entity
@Data //esta linea es de lombok y nos permite que con solo la construccion de la estrcutura de una clase pueda generar todos 
//los metodos que necesitemos getters, setters , constructor , toString , etc; sin la necesidad de escribirlo 
@Table(name = "persona")

public class Persona implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    //la anotacion NotEmpty nos permite que ya se obligatorio ingresar valores cuando creamos una nueva persona, evitando tambien cadenas vacias
    @NotEmpty
    @ValidarCaracterEspeciales
    private String nombre;
    @NotEmpty
    private String apellido;
    @NotEmpty
    @Email
    private String mail;
}
