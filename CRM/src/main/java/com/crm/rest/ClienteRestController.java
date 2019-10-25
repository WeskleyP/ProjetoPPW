package com.crm.rest;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.crm.model.Cliente;
import com.crm.model.ClientePessoaFisica;
import com.crm.service.ClienteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping(value = "/rest", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClienteRestController {
	@Autowired
	private ClienteService clienteService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarClienteById(@RequestParam("id") Long id) {
		Cliente cliente = clienteService.findClienteById(id);
		
		return cliente == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(cliente);
	}
	@ResponseBody
	@RequestMapping(value = "/cliente", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cliente> buscarCliente(String nome) {
		List<Cliente> listaCliente = clienteService.findClienteByName(nome);
		return listaCliente;
	}
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/cliente/salvar_cliente", method = RequestMethod.POST)
	public ResponseEntity<Cliente> salvarClienteModel(@RequestBody Cliente cliente) {
		System.out.println(cliente);
//		cliente = clienteService.saveCliente(cliente);
		return ResponseEntity.ok(cliente);
	}
}
