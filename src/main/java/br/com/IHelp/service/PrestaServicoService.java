package br.com.IHelp.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.IHelp.model.PrestaServico;
import br.com.IHelp.repository.PrestaServicoRepository;
import br.com.IHelp.utils.SenhaUtils;

/**
 * Classe responsável por conter a regra de negócio
 * relacionada ao prestador de serviço s
 *
 * @author jvitoroliveira
 * @since s15/01/2020
 */
@Service
public class PrestaServicoService {

	@PersistenceContext
	 private EntityManager entityManager;
	
	@Autowired
	private PrestaServicoRepository prestaServicoRepository;
	
	private SenhaUtils senhaUtils = new SenhaUtils();
	
	private static final String DISPONIVEL = "DISPONIVEL";
	
	/**
	 * Método responsavel por coletar todos os prestadores de serviço
	 * 
	 * @return
	 */
	public List<PrestaServico> pegaPrestadoresServico(){
		
		return prestaServicoRepository.findAll();
	}
	
	/**
	 *Método responsave por fazer todas as validações necessárias para inserir um usuário no banco de dados
	 *
	 * @param prestaServico
	 * @return
	 * @throws Exception
	 */
	
	public PrestaServico inserirUsuario(PrestaServico prestaServico) throws Exception {
		
//		Boolean estado = Estados.servicoDisponivel(prestaServico.getEstado().toString());
//		String estadoDoServico = EstadoServico.disponibilidadeDoServico(estado);
//		Boolean verificaEmail = verificaSeExisteEmail(prestaServico);
//		Boolean verificaCnpj = verificaSeExisteCnpj(prestaServico);
		prestaServico.setSenha(senhaUtils.gerarBCrypt(prestaServico.getSenha()));
		
//		if(estadoDoServico.equals(DISPONIVEL)) {
//			if(verificaCnpj.equals(true)&& verificaEmail.equals(true))
				return prestaServicoRepository.save(prestaServico);
//			else{
//				throw new Exception("Dado já existente");
//			}
//		}else {
//			throw new Exception("Serviço Indisponivel nesse estado!");
//		}
	}
	
	/**
	 * Método responsavel por realizar a busca de email no banco de dados
	 * 
	 * @return
	 */
	public List<String> listaEmail(){
	    TypedQuery<String> query = entityManager.createQuery("select email from PrestaServico", String.class);
	    return query.getResultList();
	  }
	
	/**
	 * Método responsavel por realizar a busca de cnpj no banco de dados
	 * 
	 * @return
	 */
	public List<String> listaCnpj(){
	    TypedQuery<String> query = entityManager.createQuery("select cnpj from PrestaServico", String.class);
	    return query.getResultList();
	  }
	
	/**
	 * Métodos para pegar a senha do usuario a partir do email
	 * @param email
	 * @return
	 */
	public List<String> listaSenha(String email){
	    TypedQuery<String> query = entityManager.createQuery("select senha from PrestaServico where email = :email" , String.class);
	    query.setParameter("email", email);
	    return query.getResultList();
	  }
	
	/**
	 * Método para fazer a verificação dentro do banco de dados se já existe uma pessoa cadastrada com o email inserido 
	 * pelo prestador de serviço
	 * 
	 * @return Boolean
	 */
	public Boolean verificaSeExisteEmail(PrestaServico prestaServico) {
		String email = listaEmail().stream()
				 							.filter(p -> p.toLowerCase().equals(prestaServico.getEmail().toLowerCase()))
				 							.collect(Collectors.joining(". "));
		
		Boolean exist = email.isEmpty() ? true : false;
		
		return exist;
	}
	
	/**
	 * Método para fazer a verificação dentro do banco de dados se já existe uma pessoa cadastrada com o cnpj inserido 
	 * pelo prestador de serviço
	 * 
	 * @return Boolean
	 */
	public Boolean verificaSeExisteCnpj(PrestaServico prestaServico) {
		String email = listaCnpj().stream()
										 .filter(p -> p.toLowerCase().equals(prestaServico.getCnpj().toLowerCase()))
										 .collect(Collectors.joining(". "));

		Boolean exist = email.isEmpty() ? true : false;
		
		return exist;
	}
	
	/**
	 * Método de verificação de senha e email para logar 
	 * 
	 * @param
	 * @return
	 */
	public Boolean loginPrestador(String email , String senha) {
		
		String senhaCriptografada = listaSenha(email).get(0);		
		Boolean senhaVerificada = senhaUtils.senhaValida(senha, senhaCriptografada);
	
		if(senhaVerificada.equals(true)) 
			  	return true;
		
		return false;
	}

}

