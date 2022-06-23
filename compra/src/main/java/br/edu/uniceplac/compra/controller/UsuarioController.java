package br.edu.uniceplac.compra.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uniceplac.compra.entidade.UsuarioEntity;
import br.edu.uniceplac.compra.pojo.Usuario;
import br.edu.uniceplac.compra.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;
	
	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@PostMapping
	public Usuario create(@RequestBody Usuario usuario){
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		
		usuarioEntity.setNome(usuario.getNome());
		usuarioEntity.setSenha(usuario.getSenha());
		usuarioEntity = usuarioRepository.save(usuarioEntity);
		
		usuario.setId(usuarioEntity.getId());
		
		return usuario;
	}
	
	@PutMapping
	public Usuario update(@RequestBody Usuario usuario){
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		
		usuarioEntity.setId(usuario.getId());
		usuarioEntity.setNome(usuario.getNome());
		usuarioEntity.setSenha(usuario.getSenha());
		usuarioEntity = usuarioRepository.save(usuarioEntity);
		
		usuario.setId(usuarioEntity.getId());
		
		return usuario;
	}
	
	@GetMapping(path = "{id}")
	public Usuario get(@PathVariable Long id){
		UsuarioEntity entity = usuarioRepository.getById(id);
		Usuario usuario = new Usuario();
		
		usuario.setId(entity.getId());
		usuario.setNome(entity.getNome());
		
		return usuario;
	}
	
	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable Long id){
		usuarioRepository.deleteById(id);
	}
	
	@GetMapping
	public List<Usuario> listAll(){
		List<UsuarioEntity> listEntity = usuarioRepository.findAll();
		List<Usuario> listPojo = new ArrayList<>();
		for(UsuarioEntity entity : listEntity){
			Usuario usuario = new Usuario();
			usuario.setId(entity.getId());
			usuario.setNome(entity.getNome());
			
			listPojo.add(usuario);
		}
		return listPojo;
	}
	
}
