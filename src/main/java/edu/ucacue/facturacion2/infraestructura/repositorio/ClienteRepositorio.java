package edu.ucacue.facturacion2.infraestructura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ucacue.facturacion2.modelo.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNombreLike(String nombre);

	List<Cliente> findByApellidoLike(String apellido);

	List<Cliente> findByCedulaLike(String cedula);

	List<Cliente> findByNombreAndApellido(String nombre, String apellido);

	/// JPQL
	@Query("select c from Cliente c where c.nombre like :nom")
	List<Cliente> buscarPorNombre(@Param("nom") String nombre);

}
