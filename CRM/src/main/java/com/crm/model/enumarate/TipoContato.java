package com.crm.model.enumarate;

public enum TipoContato {
	RESIDENCIAL("Residencial"),
	COMERCIAL("Comercial");
	
	private String descricao;

	TipoContato(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
