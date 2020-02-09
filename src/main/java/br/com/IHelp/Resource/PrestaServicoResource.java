package br.com.IHelp.Resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.IHelp.Entities.PrestaServico;
import br.com.IHelp.Service.PrestaServicoService;

@RestController
@RequestMapping(value = "/prestadoresServico")
public class PrestaServicoResource {
	
	@Autowired
	private PrestaServicoService prestaServicoService;

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<PrestaServico>> pegaTudo() {

		List<PrestaServico> listaPrestaServico = prestaServicoService.pegaPrestadoresServico();

		return ResponseEntity.ok().body(listaPrestaServico);
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<PrestaServico> cadastraUsuario(@RequestBody PrestaServico prestaServico) throws Exception{
		prestaServico = prestaServicoService.inserirUsuario(prestaServico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prestaServico.getId()).toUri();
		return ResponseEntity.created(uri).body(prestaServico);
		
	}
}
