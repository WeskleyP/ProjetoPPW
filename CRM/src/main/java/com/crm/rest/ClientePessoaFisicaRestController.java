package com.crm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.Cliente;
import com.crm.model.ClientePessoaFisica;
import com.crm.service.ClientePessoaFisicaService;

@RestController
@RequestMapping(value = "/rest", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClientePessoaFisicaRestController {
	@Autowired
	private ClientePessoaFisicaService clientePessoaFisicaService;
	@RequestMapping(value = "/cliente_pessoa_fisica/salvar_cliente_pessoa_fisica", method = RequestMethod.POST)
	public ResponseEntity<Cliente> salvarCliente(@RequestBody ClientePessoaFisica clientePessoaFisica) {
		clientePessoaFisica = clientePessoaFisicaService.saveClientePessoaFisica(clientePessoaFisica);
		return null;
	}
}
