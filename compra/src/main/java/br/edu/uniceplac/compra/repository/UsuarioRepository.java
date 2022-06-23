package br.edu.uniceplac.compra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.uniceplac.compra.entidade.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{
	
}
