package br.com.IHelp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
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
	
	@Column(name = "senha")
	private String senha;

	public PrestaServico(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
