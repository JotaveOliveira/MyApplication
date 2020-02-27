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

/**
 * 
 * @author jvitoroliveira
 * @data 26/02/2020
 */
@RestController
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	/**
	 * Método responsavel por pegar todos os usuarios dentro do banco de dados na rota /usuarios
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/usuarios")
	public ResponseEntity<List<Usuario>> pegaTudo() {

		List<Usuario> listaUsuario = usuarioService.pegaUsuarios();
		
		return ResponseEntity.ok().body(listaUsuario);
	}
	/**
	 * Método responsavel por cadastrar usuarios dentro do banco de dados na rota /cadastraUsuario
	 * 
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin
	@PostMapping(value = "/cadastraUsuario")
	public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario) throws Exception{
		usuario = usuarioService.inserirUsuario(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(usuario);
		
	}
	
	/**
	 * Método que recebe a senha e o email do usuario e realizar toda lógica para fazer login
	 * @param email
	 * @param senha
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/loginUsuario")
	public Boolean login(String email, String senha) {
		
		return usuarioService.loginUsuario(email , senha);
	}
}
