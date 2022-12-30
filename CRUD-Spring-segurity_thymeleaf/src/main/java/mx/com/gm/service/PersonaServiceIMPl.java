package mx.com.gm.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.com.gm.dao.PersonaDao;
import mx.com.gm.domain.Persona;
import mx.com.gm.repositories.PersonaRepository;


@Service
public class PersonaServiceIMPl implements PersonaServise{
    
    @Autowired // lo usamos para la inyeccion de dependecias
    private PersonaDao personaDao; //la implementacion de clase nos permite hacer de forma mas sencilla un crud con la base de datos
    @Autowired
    private PersonaRepository crud;
    

    //el transactional sirve por que si hay fallos cuando modifiquemos nuestra base de datos pueda hacer un roll back y no guarde los cambios
    //en tal caso qeu no los haya , simplemente hara un "commit", en resumen el transactional evita de que pueda haber fallos
    //siempre y cuando indiquemos que haran cada metodo con la base de datos ej:
    @Override
    @Transactional(readOnly = true) //aqui solo indicamos que sera de solo lectura(no se hara cambios en la bdd)
    public List<Persona> traerPersonas() {
        return (List<Persona>)personaDao.findAll(); //como teniamos antes , nos retorna la lista de personas
    }

    
    @Override
    @Transactional //pero aqui al no declarar nada indiacamos que si debe hacer commit o roll back si es necesario
    public void guardar(Persona persona) {
        System.out.println(persona.toString());
        personaDao.save(persona); // guardamos una persona el la base de datos
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona); //eliminamos una personas de la base de datos 
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        //le pasamos el id de la perona a travez del findById y 
        //en el caso de que no lo encuentre le decimos que retorne null(entre otras opciones)
        
        return personaDao.findById(persona.getIdPersona()).orElse(null);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> traerPersonaPorNombre(String nombre) {
        return crud.findByNombre(nombre);
    }


    
}
