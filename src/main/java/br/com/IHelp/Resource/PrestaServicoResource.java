package br.com.IHelp.Resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.IHelp.Entities.PrestaServico;
import br.com.IHelp.Service.PrestaServicoService;

@RestController
@RequestMapping(value = "/prestadoresServico")
public class PrestaServicoResource {
	
	@Autowired
	private PrestaServicoService prestaServicoService;

	@GetMapping
	public ResponseEntity<List<PrestaServico>> pegaTudo() {

		List<PrestaServico> listaPrestaServico = prestaServicoService.pegaPrestadoresServico();

		return ResponseEntity.ok().body(listaPrestaServico);
	}
	
	@PostMapping
	public ResponseEntity<PrestaServico> cadastraUsuario(@RequestBody PrestaServico prestaServico){
		prestaServico = prestaServicoService.inserirUsuario(prestaServico);
		return ResponseEntity.ok().body(prestaServico);
		
	}
}
