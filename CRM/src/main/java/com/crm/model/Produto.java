package com.crm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TAB_PRODUTO")
@SequenceGenerator(name = "PRODUTO_SEQUENCE",sequenceName = "TAB_PRODUTO_SEQUENCE", initialValue = 1,allocationSize = 1)
public class Produto {
	private Long id;
	private String nome;
	private String descricao;
	private double valor;
	private int quantidade;
	
	private Categoria categoria;
	public Produto() {

	}
	public Produto(Long id, String nome, String descricao, double valor, int quantidade) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUTO_SEQUENCE")
	@Column(name = "PRODUTO_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Size(min = 3, max = 100, message = "No minimo 3 caracteres e no maximo 100 caracteres!")
	@NotNull(message = "O nome do produto deve ser informado!")
	@Column(name = "PRODUTO_NOME", length = 100,nullable = false)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Size(min = 5, max = 50, message = "No minimo 5 caracteres e no maximo 50 caracteres!")
	@Column(name = "PRODUTO_DESC", length = 100, nullable = true)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@NotNull(message = "O valor do produto deve ser informado!")
	@Column(name = "PRODUTO_VALOR", nullable = false)
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	@NotNull(message = "A quantidade do produto deve ser informado!")
	@Column(name = "PRODUTO_QUANTIDADE", nullable = false)
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	@ManyToOne(targetEntity = Categoria.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORIA_ID", nullable = false, referencedColumnName = "CATEGORIA_ID", foreignKey = @ForeignKey(name="FK_PRODUTO_CATEGORIA"))
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
