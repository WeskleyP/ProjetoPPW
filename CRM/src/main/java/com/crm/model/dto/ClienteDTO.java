package com.crm.model.dto;

import javax.validation.constraints.NotNull;

public class ClienteDTO {
	
	private String nome;
	private String email;
	
	@NotNull(message ="O campo nome dever ser informado!")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@NotNull(message ="O campo email dever ser informado!")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
