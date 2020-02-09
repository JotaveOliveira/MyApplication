package br.com.IHelp.Types;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access=AccessLevel.PRIVATE)
public enum Estados {
	
	SÃO_PAULO("SÃO PAULO");
	
	private String state;
	
		
		private static List<Estados> estados = Arrays.asList(Estados.values());
		
		public static Boolean servicoDisponivel(String estado) {
			
			List<Estados> states = estados.stream()
										  .filter(state -> state.getState().trim().toUpperCase().equals(estado.trim().toUpperCase()))
										  .collect(Collectors.toList());
			
			Boolean resultado = states.isEmpty() ? false : true;
			
			return resultado;
			
	}
}
