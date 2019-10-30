package com.crm.rest.response;

import java.util.Map;

import com.crm.model.dto.ClienteDTO;

public class ErroCadastroCliente {
	
	private ClienteDTO clienteDTO;
	private boolean validate;
	private Map<String, String> erroMensagem;
	private String mensagem;
	
	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}
	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	public Map<String, String> getErroMensagem() {
		return erroMensagem;
	}
	public void setErroMensagem(Map<String, String> erroMensagem) {
		this.erroMensagem = erroMensagem;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
