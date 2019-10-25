package com.crm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "TAB_CLIENTE")
@SequenceGenerator(name = "CLIENTE_SEQUENCE",sequenceName = "TAB_CLIENTE_SEQUENCE", initialValue = 1,allocationSize = 1)
@SQLDelete(sql = "UPDATE TAB_CLIENTE SET registroDeletado = true WHERE cliente_id = ?")
@Where(clause = "registroDeletado = false")
public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -423197831627683718L;
	private long id;
	private String nome;
	private String email;
	
	private ClientePessoaFisica clientePessoaFisica;
	private List<Telefone> listaTelefones;
	
	private boolean registroDeletado = false;
	
	public Cliente() {
		
	}
	public Cliente(long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_SEQUENCE")
	@Column(name = "CLIENTE_ID")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Size(min = 3, max = 100, message = "No minimo 3 caracteres e no maximo 100 caracteres!")
	@NotBlank(message = "O nome da pesssoa deve ser informado!")
	@NotNull(message = "O nome da pesssoa deve ser informado!")
	@Column(name = "CLIENTE_NOME", length = 100,nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Email(message = "Email Invalido")
	@Size(min = 5, max = 100, message = "No minimo 5 caracteres e no maximo 100 caracteres!")
	@NotBlank(message = "O e-mail da pesssoa deve ser informado!")
	@NotNull(message = "O e-mail da pesssoa deve ser informado!")
	@Column(name = "CLIENTE_EMAIL", length = 100,nullable = false, unique = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@OneToOne(mappedBy = "cliente", fetch = FetchType.LAZY)
	public ClientePessoaFisica getClientePessoaFisica() {
		return clientePessoaFisica;
	}
	public void setClientePessoaFisica(ClientePessoaFisica clientePessoaFisica) {
		this.clientePessoaFisica = clientePessoaFisica;
	}
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	public List<Telefone> getListaTelefones() {
		return listaTelefones;
	}
	public void setListaTelefones(List<Telefone> listaTelefones) {
		this.listaTelefones = listaTelefones;
	}
	
	@Column(name = "registroDeletado")
	public boolean isRegistroDeletado() {
		return registroDeletado;
	}
	public void setRegistroDeletado(boolean registroDeletado) {
		this.registroDeletado = registroDeletado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}