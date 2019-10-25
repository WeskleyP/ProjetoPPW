package com.crm.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.repository.Temporal;

import com.crm.model.enumarate.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TAB_CLIENTE_PESSOA_FISICA")
@SequenceGenerator(name = "CLIENTE_PESSOA_FISICA_SEQUENCE", sequenceName = "TAB_CLIENTE_PESSOA_FISICA_SEQUENCE", initialValue = 1, allocationSize = 1)

public class ClientePessoaFisica {
	private Long id;
	private Date dateNasc;
	private String nomePai;
	private String nomeMae;
	private Sexo sexo;
	private String cpf;
	
	private Cliente cliente;
	
	public ClientePessoaFisica() {

	}
	public ClientePessoaFisica(Long id, Date dateNasc, String nomePai, String nomeMae, Sexo sexo, String cpf) {
		this.id = id;
		this.dateNasc = dateNasc;
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
		this.sexo = sexo;
		this.cpf = cpf;
	}
	@Id
	@Column(name = "CLIENTE_PESSOA_FISICA_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_PESSOA_FISICA_SEQUENCE")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "CLIENTE_PESSOA_FISICA_DATA_NASC")
	@NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone = "UTC")
	public Date getDateNasc() {
		return dateNasc;
	}
	public void setDateNasc(Date dateNasc) {
		this.dateNasc = dateNasc;
	}
	@Size(min = 3, max = 100, message = "No minimo 3 caracteres e no maximo 100 caracteres!")
	@NotBlank(message = "O nome do pai deve ser informado!")
	@NotNull(message = "O nome do pai deve ser informado!")
	@Column(name = "CLIENTE_PESSOA_FISICA_NOME_PAI")
	public String getNomePai() {
		return nomePai;
	}
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	@Size(min = 3, max = 100, message = "No minimo 3 caracteres e no maximo 100 caracteres!")
	@NotBlank(message = "O nome da mãe deve ser informado!")
	@NotNull(message = "O nome da mãe deve ser informado!")
	@Column(name = "CLIENTE_PESSOA_FISICA_NOME_MAE")
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	@NotNull(message = "O sexo deve ser informado!")
	@Column(name = "CLIENTE_PESSOA_FISICA_SEXO")
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	@Size(min = 3, max = 15, message = "O CPF deve ter 11 caracteres!")
	@NotBlank(message = "O CPF deve ser informado!")
	@NotNull(message = "O CPF deve ser informado!")
	@Column(name = "CLIENTE_PESSOA_FISICA_CPF")
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	@MapsId
	@OneToOne(targetEntity = Cliente.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTE_ID", referencedColumnName = "CLIENTE_ID")
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
