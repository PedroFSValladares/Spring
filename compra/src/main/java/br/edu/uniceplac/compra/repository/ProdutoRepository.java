package br.edu.uniceplac.compra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.uniceplac.compra.entidade.ProdutoEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

}
