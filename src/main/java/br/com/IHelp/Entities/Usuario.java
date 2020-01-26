package br.com.IHelp.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;
	
	@Column(name = "cpf_usuario")
	private String cpf;
	
	@Column(name = "endereco_usuario")
	private String endereco;
	
	@Column(name = "email_usuario")
	private String email;
	
	@Column(name = "data_nascimento_usuario")
	private String dataNascimento;
	
	@Column(name = "estado_usuario")
	private String estado;	
	
	@Column(name = "cidade_usuario")
	private String cidade;	
	
	@Column(name = "cep_usuario")
	private String cep;
	
	@OneToOne
	private Login login;
}
