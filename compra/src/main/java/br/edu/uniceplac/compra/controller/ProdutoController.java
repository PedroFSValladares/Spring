package br.edu.uniceplac.compra.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uniceplac.compra.entidade.ProdutoEntity;
import br.edu.uniceplac.compra.pojo.Produto;
import br.edu.uniceplac.compra.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	private final ProdutoRepository produtoRepository;
	
	public ProdutoController(ProdutoRepository produtoRepository){
		this.produtoRepository = produtoRepository;
	}
	
	@PostMapping
	public Produto post(@RequestBody Produto produto){
		ProdutoEntity produtoEntity = new ProdutoEntity();
		
		produtoEntity.setNome(produto.getNome());
		produtoEntity.setCategoria(produto.getCategoria());
		produtoEntity.setValor(produto.getValor());
		produtoRepository.save(produtoEntity);
		
		produto.setId(produtoEntity.getId());
		
		return produto;
	}
	
	@PutMapping
	public Produto update(@RequestBody Produto produto){
		ProdutoEntity produtoEntity = new ProdutoEntity();
		
		produtoEntity.setId(produto.getId());
		produtoEntity.setNome(produto.getNome());
		produtoEntity.setCategoria(produto.getCategoria());
		produtoEntity.setValor(produto.getValor());
		produtoRepository.save(produtoEntity);
		
		produto.setId(produtoEntity.getId());
		
		return produto;
	}
	
	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable Long id){
		produtoRepository.deleteById(id);
	}
	
	@GetMapping(path = "{id}")
	public Produto get(@PathVariable Long id){
		ProdutoEntity produtoEntity = produtoRepository.getById(id);
		Produto produto = new Produto();
		
		produto.setNome(produtoEntity.getNome());
		produto.setCategoria(produtoEntity.getCategoria());
		produto.setValor(produtoEntity.getValor());
		produto.setId(produtoEntity.getId());
		
		return produto;
	}
	
	@GetMapping
	public List<Produto> listAll(){
		List<ProdutoEntity> listProdutos = produtoRepository.findAll();
		List<Produto> produtos = new ArrayList<Produto>();
		
		for(ProdutoEntity p : listProdutos){
			Produto produto = new Produto();
			
			produto.setNome(p.getNome());
			produto.setCategoria(p.getCategoria());
			produto.setValor(p.getValor());
			produto.setId(p.getId());
			
			produtos.add(produto);
		}
		
		return produtos;
	}
}
