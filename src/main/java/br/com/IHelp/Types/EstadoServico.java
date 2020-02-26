package br.com.IHelp.Types;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

/**
 * 
 * @author jvitoroliveira
 * @data 23/01/2020
 */
@Getter
public enum EstadoServico {
	DISPONIVEL,
	INDISPONIVEL,
	CONCLUIDO,
	EM_ANDAMENTO,
	ENCERRADO;
	
	private static List<EstadoServico> estadoServico = Arrays.asList(EstadoServico.values());
	
	/**
	 * Método responsavel por verificar se o serviço está disponivel ou não 
	 * 
	 * @param servicoEstado
	 * @return
	 */
	public static String disponibilidadeDoServico(Boolean servicoEstado) {
		
		String diponibilidade = servicoEstado.equals(true) ? estadoServico.get(0).toString()  : estadoServico.get(1).toString();
		
		return diponibilidade;
	}
}
