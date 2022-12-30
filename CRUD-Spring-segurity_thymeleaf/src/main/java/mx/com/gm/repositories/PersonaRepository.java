package mx.com.gm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import mx.com.gm.domain.Persona;

@Repository("personaRepository")
public interface PersonaRepository extends JpaRepository<Persona, Serializable>{
    @Query(value = "select * from persona p where p.nombre like %:nombre% ", nativeQuery = true)
    List<Persona> findByNombre(@Param("nombre") String nombre);
}
