package com.crm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Cliente;
import com.crm.repository.ClienteRepository;
import com.crm.repository.filter.ClienteFilter;
import com.crm.service.ClienteService;
import com.crm.service.exceptions.EmailClienteExistente;
@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Override
	public Cliente saveCliente(Cliente cliente) {
		Optional<Cliente> optionalCliente = findClienteByEmail(cliente.getEmail());
		if(optionalCliente.isPresent()) {
			throw new EmailClienteExistente("Email já cadastrado");
		}
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente update(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void remove(Cliente cliente) {
		clienteRepository.deleteById(cliente.getId());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {		
		return clienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findClienteById(Long id) {
		return clienteRepository.getOne(id);
	}

	@Override
	public Page<Cliente> listaClienteComPaginacao(ClienteFilter clienteFilter, Pageable pageable) {
		return clienteRepository.listaClienteComPaginacao(clienteFilter, pageable);
	}

	@Override
	public Optional<Cliente> findClienteByEmail(String email) {
		return clienteRepository.findClienteByEmail(email);
	}

	@Override
	public List<Cliente> findClienteByName(String nome) {
		return clienteRepository.findClienteByName(nome);
	}

}