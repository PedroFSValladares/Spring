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

import br.edu.uniceplac.compra.entidade.CarrinhoEntity;
import br.edu.uniceplac.compra.entidade.ProdutoEntity;
import br.edu.uniceplac.compra.entidade.UsuarioEntity;
import br.edu.uniceplac.compra.pojo.Carrinho;
import br.edu.uniceplac.compra.pojo.Produto;
import br.edu.uniceplac.compra.pojo.Usuario;
import br.edu.uniceplac.compra.repository.CarrinhoRepository;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {
	private final CarrinhoRepository carrinhoRepository;
	
	public CarrinhoController(CarrinhoRepository carrinhoRepository){
		this.carrinhoRepository = carrinhoRepository;
	}
	
	@PostMapping
	public Carrinho create(@RequestBody Carrinho carrinho){
		CarrinhoEntity entity = new CarrinhoEntity();
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		List<ProdutoEntity> listaEntity = new ArrayList<>();
		
		usuarioEntity.setNome(carrinho.getUsuario().getNome());
		usuarioEntity.setSenha(carrinho.getUsuario().getSenha());
		usuarioEntity.setId(carrinho.getUsuario().getId());
		
		entity.setUsuario(usuarioEntity);

		for(Produto p : carrinho.getListaProduto()){
			ProdutoEntity produtoEntity = new ProdutoEntity();
			
			produtoEntity.setId(p.getId());
			produtoEntity.setNome(p.getNome());
			produtoEntity.setCategoria(p.getCategoria());
			produtoEntity.setValor(p.getValor());
			
			listaEntity.add(produtoEntity);
		}
		
		entity.setListaProdutos(listaEntity);
		entity = carrinhoRepository.save(entity);
		carrinho.setId(entity.getId());
				
		return carrinho;
	}
	
	@PutMapping
	public Carrinho update(@RequestBody Carrinho carrinho){
		CarrinhoEntity entity = new CarrinhoEntity();
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		List<ProdutoEntity> listaEntity = new ArrayList<>();
		
		entity.setId(carrinho.getId());
		
		usuarioEntity.setId(carrinho.getUsuario().getId());
		usuarioEntity.setNome(carrinho.getUsuario().getNome());
		usuarioEntity.setSenha(carrinho.getUsuario().getSenha());
		
		entity.setUsuario(usuarioEntity);

		for(Produto p : carrinho.getListaProduto()){
			ProdutoEntity produtoEntity = new ProdutoEntity();
			
			produtoEntity.setId(p.getId());
			produtoEntity.setNome(p.getNome());
			produtoEntity.setCategoria(p.getCategoria());
			produtoEntity.setValor(p.getValor());
			
			listaEntity.add(produtoEntity);
		}
		
		entity.setListaProdutos(listaEntity);
		entity = carrinhoRepository.save(entity);
		carrinho.setId(entity.getId());
				
		return carrinho;
	}
	
	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable Long id){
		carrinhoRepository.deleteById(id);
	}
	
	@GetMapping(path = "{id}")
	public Carrinho get(@PathVariable Long id){
		CarrinhoEntity carrinhoEntity = carrinhoRepository.getById(id);
		Carrinho carrinho = new Carrinho();
		Usuario usuario = new Usuario();
		ArrayList<Produto> listaProduto = new ArrayList<Produto>();
		
		usuario.setId(carrinhoEntity.getUsuario().getId());
		usuario.setNome(carrinhoEntity.getUsuario().getNome());
		
		for(ProdutoEntity produtoEntity : carrinhoEntity.getListaProdutos()){
			Produto produto = new Produto();
			
			produto.setId(produtoEntity.getId());
			produto.setNome(produtoEntity.getNome());
			produto.setCategoria(produtoEntity.getCategoria());
			produto.setValor(produtoEntity.getValor());
			
			listaProduto.add(produto);
		}
		
		carrinho.setId(carrinhoEntity.getId());
		carrinho.setUsuario(usuario);
		carrinho.setListaProduto(listaProduto);
		return carrinho;
	}
}
