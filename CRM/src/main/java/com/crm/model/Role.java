package com.crm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.web.bind.annotation.CookieValue;

import lombok.EqualsAndHashCode;

@Entity
@Table(name = "TAB_ROLE")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "ROLE_SEQUENCE", sequenceName = "TAB_ROLE_SEQUENCE", allocationSize = 1, initialValue = 1)
public class Role{
	@EqualsAndHashCode.Include
	private Long idRole;
	private String nomeRole;

	List<Usuario> usuarios;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "role")
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Role() {
	}

	public Role(Long idRole, String nomeRole) {
		this.idRole = idRole;
		this.nomeRole = nomeRole;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQUENCE")
	@Column(name = "ROLE_ID")
	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	@Size(min = 3, max = 100, message = "No minimo 3 caracteres e no maximo 100 caracteres!")
	@NotBlank(message = "O nome da função deve ser informado!")
	@NotNull(message = "O nome da função deve ser informado!")
	@Column(name = "ROLE_NOME")
	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

}
