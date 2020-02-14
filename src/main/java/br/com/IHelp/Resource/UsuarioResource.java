package br.com.IHelp.Resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.IHelp.Entities.Usuario;
import br.com.IHelp.Service.UsuarioService;

@RestController
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	@CrossOrigin
	@GetMapping(value = "/usuarios")
	public ResponseEntity<List<Usuario>> pegaTudo() {

		List<Usuario> listaUsuario = usuarioService.pegaUsuarios();
		
		return ResponseEntity.ok().body(listaUsuario);
	}
	
	@CrossOrigin
	@PostMapping(value = "/cadastraUsuario")
	public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario) throws Exception{
		usuario = usuarioService.inserirUsuario(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
		
	}
}
