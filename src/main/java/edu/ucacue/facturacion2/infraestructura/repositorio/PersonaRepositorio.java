package edu.ucacue.facturacion2.infraestructura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ucacue.facturacion2.modelo.Persona;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {
	
	List<Persona> findByNombreLike(String nombre);
	List<Persona> findByApellidoLike(String apellido);
	List<Persona> findByCedulaLike(String cedula);
	List<Persona> findByNombreAndApellido(String nombre, String apellido);
	
	
	///JPQL
	@Query("select p from Persona p where p.nombre like :nom")
	List<Persona> buscarPorNombre(@Param("nom") String nombre);
	

}
