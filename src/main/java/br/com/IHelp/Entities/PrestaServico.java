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
@Table(name = "presta_servico")
public class PrestaServico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_presta_servico")
	private Long id;
	
	@Column(name = "cnpj_presta_servico")
	private String cnpj;
	
	@Column(name = "nome_presta_servico")
	private String nome;
	
	@Column(name = "estado_presta_servico")
	private String estado;
	
	@Column(name = "cidade_presta_servico")
	private String cidade;
	
	@Column(name = "email_presta_servico")
	private String email;
	
	@Column(name = "data_nascimento_presta_servico")
	private String dataNascimento;
	
	@Column(name = "endereco_presta_servico")
	private String endereco;
	
	@Column(name = "cep_presta_servico")
	private String cep;
	
	@OneToOne
	private Login login;
}
