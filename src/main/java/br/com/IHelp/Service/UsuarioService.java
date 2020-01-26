package br.com.IHelp.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.IHelp.Entities.Usuario;
import br.com.IHelp.Repository.UsuarioRepository;
import br.com.IHelp.Types.EstadoServico;
import br.com.IHelp.Types.Estados;

@Service
public class UsuarioService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private static final String DISPONIVEL = "DISPONIVEL";
	
	public List<Usuario> pegaUsuarios(){
		
		return usuarioRepository.findAll();
	}
	
	public Usuario inserirUsuario(Usuario usuario) {
		
		Boolean estado = Estados.servicoDisponivel(usuario.getEstado().toString());
		String estadoDoServico = EstadoServico.disponibilidadeDoServico(estado);
		Boolean verificaEmail = verificaSeExisteEmail(usuario);
		
		if(estadoDoServico.equals(DISPONIVEL) && verificaEmail.equals(false)) {
			return usuarioRepository.save(usuario);
		}else {
			return null;
		}
	}
	
	public List<Usuario> listaEmail(){
	    TypedQuery<Usuario> query = entityManager.createQuery("select email_usuario from usuario", Usuario.class);
	    return query.getResultList();
	  }
	
	public List<Usuario> listaCpf(){
	    TypedQuery<Usuario> query = entityManager.createQuery("select cpf_usuario from usuario", Usuario.class);
	    return query.getResultList();
	  }
	
	public Boolean verificaSeExisteEmail(Usuario usuario) {
		String email = listaEmail().stream()
										 .filter(p -> p.getEmail().toString().trim().toLowerCase().equals(usuario.getEmail().toString().trim().toLowerCase()))
										 .map(p -> p.getEmail().toString())
										 .collect(Collectors.joining("* "));
		
		Boolean exist = email.isEmpty() ? false : true;
		
		return exist;
	}
}
