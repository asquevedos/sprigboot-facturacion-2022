package edu.ucacue.facturacion2.infraestructura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import edu.ucacue.facturacion2.modelo.Producto;

public interface ProductoRepository  extends JpaRepository<Producto, Integer> {
	
	///JPQL
	@Query("select p from Producto p where p.nombre like :nom")
	List<Producto> buscarPorNombre(@Param("nom") String nombre);
	

}
