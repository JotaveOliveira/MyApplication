package br.com.IHelp.controller;

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

import br.com.IHelp.model.PrestaServico;
import br.com.IHelp.service.PrestaServicoService;

/**
 * Classe responsável por conter 
 * o ponto de entrada relacionado
 * a um prestador de servico
 * 
 * @author João Vitor 
 * @data 15/01/2020
 */
@RestController
public class PrestadorSerivicoController {
	
	@Autowired
	private PrestaServicoService prestaServicoService;

	/**
	 * Método responsavel por listar todos os prestadores de serviço na rota /prestadores
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/prestadores")
	public ResponseEntity<List<PrestaServico>> pegaTudo() {

		List<PrestaServico> listaPrestaServico = prestaServicoService.pegaPrestadoresServico();

		return ResponseEntity.ok().body(listaPrestaServico);
	}
	
	/**
	 * Método responsável por cadastrar um prestador de serviço dentro do banco de dados na rota /prestadoresServico
	 * 
	 * @param prestaServico
	 * @return
	 * @throws Exception
	 */
	@CrossOrigin
	@PostMapping(value = "/prestadoresServico")
	public ResponseEntity<PrestaServico> cadastraUsuario(@RequestBody PrestaServico prestaServico) throws Exception{
		prestaServico = prestaServicoService.inserirUsuario(prestaServico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prestaServico.getId()).toUri();
		return ResponseEntity.created(uri).body(prestaServico);
		
	}
	/**
	 * Método que recebe a senha e o email do prestador de serviço e realizar toda lógica para fazer login
	 * @param email
	 * @param senha
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/loginPrestador")
	public Boolean login(String email, String senha) {
		
		return prestaServicoService.loginPrestador(email , senha);
		
	}
}
