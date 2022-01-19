package edu.ucacue.facturacion2.infraestructura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucacue.facturacion2.modelo.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Integer> {

}
