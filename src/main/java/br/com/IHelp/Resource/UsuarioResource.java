package br.com.IHelp.Resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.IHelp.Entities.Usuario;
import br.com.IHelp.Service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> pegaTudo() {

		List<Usuario> listaUsuario = usuarioService.pegaUsuarios();
		
		return ResponseEntity.ok().body(listaUsuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario){
		usuario = usuarioService.inserirUsuario(usuario);
		
		return ResponseEntity.ok().body(usuario);
		
	}
}
