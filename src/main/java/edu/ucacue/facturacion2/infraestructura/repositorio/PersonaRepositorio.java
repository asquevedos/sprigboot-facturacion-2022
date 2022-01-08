package edu.ucacue.facturacion2.infraestructura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucacue.facturacion2.modelo.Persona;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {

}
