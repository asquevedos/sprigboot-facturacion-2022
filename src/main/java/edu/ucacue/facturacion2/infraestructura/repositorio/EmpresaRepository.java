package edu.ucacue.facturacion2.infraestructura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucacue.facturacion2.modelo.Empresa;

public interface EmpresaRepository  extends JpaRepository<Empresa, Integer> {

}
