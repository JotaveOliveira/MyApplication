package br.com.IHelp.Resource;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.IHelp.Entities.Usuario;
import br.com.IHelp.Types.EstadoServico;
import br.com.IHelp.Types.Estados;

@Controller
public class UsuarioResource {
	
	private static final String INDISPONIVEL = "INDISPONIVEL";
	
	@GetMapping(value = "/usuarios")
	public ResponseEntity<Usuario> pegaTudo(){
		
		Usuario user = new Usuario("Carla", "234.234.323.32", "2342-3234", "Carla@gmail.com", new Date(12/02/2000), "São Paulo");
		
		Boolean estado = Estados.servicoDisponivel(user.getEstado());
		
		String estadoDoServiço = EstadoServico.disponibilidadeDoServico(estado);
		
		if(estadoDoServiço.equals(INDISPONIVEL)) {
			return null;
		}else {
			return ResponseEntity.ok().body(user);
		}
		
	}
}
