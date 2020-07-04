package br.com.IHelp.enumerator;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author jvitoroliveira
 * @since 23/01/2020
 */
public enum Estados {
	
	SÃO_PAULO("SÃO PAULO", Arrays.asList(1));
	
	private String state;
	private List<Integer> idEstado;

	/**
	 * Constutor privado responsável por
	 * evitar a instanciação
	 * e popular os atributos
	 * @param estado
	 * @param idEstado
	 */
	private Estados(String estado ,List<Integer> idEstado){
		this.idEstado = idEstado;
		this.state = estado;
	}

	/**
	 * Método get responsável por devolver um estado
	 *
	 * @return estado
	 */
	public String getState() {
		return state;
	}

	/**
	 * Método get responsável por devolver um idEstado
	 *
	 * @return idEstado
	 */
	public List<Integer> getIdEstado() {
		return idEstado;
	}

	/**
	 * Este método serve para verificar se o serviço está disponivel no estado ou não , verificando o estado está dentro do enum
	 *
	 * @param estado
	 * @return
	 */
	public static List<Integer> servicoDisponivel(String estado) throws Exception {

		return  Arrays.stream(Estados.values()).filter(state -> state.getState().equals(estado))
				                      .map(Estados::getIdEstado)
				                      .findFirst()
				                      .orElseThrow(() -> new Exception("Não foi encontrado esse estado"));
	}
}
