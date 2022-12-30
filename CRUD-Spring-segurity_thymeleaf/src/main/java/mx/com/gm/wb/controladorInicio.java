package mx.com.gm.wb;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.service.PersonaServise;

//ahora el controlodor ya noe s de tipo rest sino de tipó mvc
@Controller
@Slf4j
public class controladorInicio {

    @Autowired //hace los mismo que instaciar, pero en este caso inyecta una objeto del cual indicamos sin instanciarlo 
    private PersonaServise personaServise;

    @GetMapping("/")//este es el pad de inicio es el primer lugar donde va a ir cuando inicie la pagina 
    public String inicio(Model model , @AuthenticationPrincipal User user){
        model.addAttribute("personas",personaServise.traerPersonas());
        model.addAttribute("usuario", user);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }


    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors error){
        if(error.hasErrors()){
            return "modificar";
        }
        System.out.println("entre al guardar");
        personaServise.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaServise.eliminar(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
       persona = personaServise.encontrarPersona(persona);
       model.addAttribute(persona);
       return "modificar";
    }

    @GetMapping("/traer")
    public String traerPersonas(Persona persona, String nombre , Model model,  @AuthenticationPrincipal User user ){
        model.addAttribute("personas",personaServise.traerPersonaPorNombre(nombre));
        model.addAttribute("usuario", user);
        return "index";
    }
}
