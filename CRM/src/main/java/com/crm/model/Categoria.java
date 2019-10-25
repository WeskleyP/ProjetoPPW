package com.crm.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name= "TAB_CATEGORIA")
@SequenceGenerator(name = "CATEGORIA_SEQUENCE",sequenceName = "TAB_CATEGORIA_SEQUENCE" ,initialValue = 1, allocationSize = 1)
public class Categoria {
	private Long id;
	private String nome;
	
	private List<Produto> listaProdutos;
	
	public Categoria() {

	}
	public Categoria(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIA_SEQUENCE")
	@Column(name = "CATEGORIA_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Size(min = 3, max = 100, message = "No minimo 3 caracteres e no maximo 100 caracteres!")
	@NotBlank(message = "A categoria deve ser informada!")
	@NotNull(message = "A categoria deve ser informada!")
	@Column(name = "CATEGORIA_NOME", length = 100,nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}	
}