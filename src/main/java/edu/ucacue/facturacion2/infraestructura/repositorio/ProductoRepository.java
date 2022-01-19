package edu.ucacue.facturacion2.infraestructura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucacue.facturacion2.modelo.Producto;

public interface ProductoRepository  extends JpaRepository<Producto, Integer> {

}
