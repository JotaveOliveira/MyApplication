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
import br.com.IHelp.Utils.SenhaUtils;

/**
 * 
 * @author jvitoroliveira
 * @data 26/02/2020
 */
@Service
public class UsuarioService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private SenhaUtils senhaUtils = new SenhaUtils();
	
	private static final String DISPONIVEL = "DISPONIVEL";
	
	/**
	 * Método responsavel por pegar todos os usuarios do banco de dados
	 * @return
	 */
	public List<Usuario> pegaUsuarios(){
		
		return usuarioRepository.findAll();
	}
	
	/**
	 * Método responsável por aplicar todas as regras de negócio para inserir um usuário no banco de dados
	 * 
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public Usuario inserirUsuario(Usuario usuario) throws Exception {
		
		Boolean estado = Estados.servicoDisponivel(usuario.getEstado());
		String estadoDoServico = EstadoServico.disponibilidadeDoServico(estado);
		Boolean verificaEmail = verificaSeExisteEmail(usuario);
		Boolean verificaCpf = verificaSeExisteCpf(usuario);
		usuario.setSenha(senhaUtils.gerarBCrypt(usuario.getSenha()));
		
		if(estadoDoServico.equals(DISPONIVEL)) {
			if(verificaCpf.equals(true)&& verificaEmail.equals(true)) {
				return usuarioRepository.save(usuario);
			}else{
				throw new Exception("Dado já existente");
			}
		}else {
			throw new Exception("Serviço Indisponivel nesse estado!");
		}
	}
	
	/**
	 * Método responsavel por buscar todos os emails dentro do banco de dados
	 * 
	 * @return
	 */
	public List<String> listaEmail(){
	    TypedQuery<String> query = entityManager.createQuery("select email from Usuario", String.class);
	    return query.getResultList();
	  }
	
	/**
	 * Método responsavel por buscar todos cpf dentro do banco de dados
	 * 
	 * @return
	 */
	public List<String> listaCpf(){
	    TypedQuery<String> query = entityManager.createQuery("select cpf from Usuario", String.class);
	    return query.getResultList();
	  }
	
	/**
	 * Métodos para pegar a senha do usuario a partir do email
	 * @param email
	 * @return
	 */
	private List<String> listaSenha(String email) {
		TypedQuery<String> query = entityManager.createQuery("select senha from Usuario where email = '" 
							+ email.toString() + "'"  , String.class);
	    return query.getResultList();
	}
	
	/**
	 * Método responsavel por verificar se o email do usuario já existe dentro do banco de dados
	 * 
	 * @param usuario
	 * @return
	 */
	public Boolean verificaSeExisteEmail(Usuario usuario) {
		String email = listaEmail().stream()
											.filter(p -> p.toLowerCase().equals(usuario.getEmail().toLowerCase()))
											.collect(Collectors.joining(". "));
		
		Boolean exist = email.isEmpty() ? true : false;
		
		return exist;
	}
	
	/**
	 * Método responsavel por verificar se existe cpf do usuario dentro do banco de dados 
	 * 
	 * @param usuario
	 * @return
	 */
	public Boolean verificaSeExisteCpf(Usuario usuario) {
		String email = listaCpf().stream()
										 .filter(p -> p.toLowerCase().equals(usuario.getCpf().toLowerCase()))
										 .collect(Collectors.joining(". "));

		Boolean exist = email.isEmpty() ? true : false;

		return exist;
	}

	/**
	 * Método de verificação de senha e email para logar 
	 * 
	 * @param prestaServico
	 * @return
	 */
	public Boolean loginUsuario(String email, String senha) {
		
		String senhaCriptografada = listaSenha(email).get(0);
		
		Boolean senhaVerificada = senhaUtils.senhaValida(senha, senhaCriptografada);
		
		if(senhaVerificada.equals(true)) 
			return true;
	
		return false;
	}
}
