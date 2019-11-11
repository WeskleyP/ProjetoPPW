package com.crm.rest;

import org.springframework.web.bind.annotation.ExceptionHandler;
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
import com.crm.model.dto.ClienteDTO;
import com.crm.rest.response.ErroCadastroCliente;
import com.crm.service.ClienteService;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.aspectj.weaver.NewFieldTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

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
		validaNomePesquisa(nome);
		List<Cliente> listaCliente = clienteService.findClienteByName(nome);
		return listaCliente;
	}

	private void validaNomePesquisa(String nome) {
		if(StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();	
		}
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> pegarErroIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/cliente/salvar_cliente", method = RequestMethod.POST)
	public ResponseEntity<ErroCadastroCliente> salvarClienteModel(@RequestBody ClienteDTO clienteDTO,
			BindingResult result) {
		boolean erro = false;
		ErroCadastroCliente erroCadastroCliente = new ErroCadastroCliente();
		if (StringUtils.isEmpty(clienteDTO.getNome())) {
			erroCadastroCliente.setValidate(false);
			erroCadastroCliente.getErroMensagem().put("nome", "O nome deve ser informado");
			erroCadastroCliente.setMensagem(null);
			erro = true;
		}
		if(StringUtils.isEmpty(clienteDTO.getNome())){
			erroCadastroCliente.setValidate(false);
			erroCadastroCliente.getErroMensagem().put("email", "O email deve ser informado");
			erroCadastroCliente.setMensagem(null);
			erro = true;
		}
		if(erro == false){
			Cliente cliente = new Cliente();
			cliente.setNome(clienteDTO.getNome());
			cliente.setEmail(clienteDTO.getEmail());
			cliente = clienteService.saveCliente(cliente);
			erroCadastroCliente.setIdClasse(cliente.getId());
			erroCadastroCliente.setValidate(true);
			erroCadastroCliente.setErroMensagem(null);
			erroCadastroCliente.setMensagem("Pessoa Cadastrada com Sucesso!");
		}
		return ResponseEntity.ok(erroCadastroCliente);
	}
}
