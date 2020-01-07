package br.com.IHelp.domain.Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario extends Login implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "nome_usuario")
	private String nome;
	
	@Column(name = "cpf_usuario")
	private String cpf;
	
	@Column(name = "telefone_usuario")
	private String telefone;
	
	@Column(name = "email_usuario")
	private String email;
	
	@Column(name = "data_nascimento_usuario")
	private Date dataNascimento;
	
	@Column(name = "estado_usuario")
	private String estado;	
}
