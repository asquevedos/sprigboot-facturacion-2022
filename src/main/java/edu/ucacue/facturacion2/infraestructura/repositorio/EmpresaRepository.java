package edu.ucacue.facturacion2.infraestructura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucacue.facturacion2.modelo.Empresa;

public interface EmpresaRepository  extends JpaRepository<Empresa, Integer> {
	
	List<Empresa>  findByRazonSocialLike(String razonSocial);
	List<Empresa>  findByTelefonoLike(String telefono);
	List<Empresa> findByRucLike(String ruc);

}
