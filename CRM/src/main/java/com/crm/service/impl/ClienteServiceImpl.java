package com.crm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Cliente;
import com.crm.model.Telefone;
import com.crm.repository.ClienteRepository;
import com.crm.repository.TelefoneRepository;
import com.crm.repository.filter.ClienteFilter;
import com.crm.service.ClienteService;
import com.crm.service.exceptions.EmailClienteExistente;
@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TelefoneRepository telefoneRepository;
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
		if(cliente.getListaTelefones().size() != -1) {
			for(Telefone telefone : cliente.getListaTelefones()) {
				telefoneRepository.deleteById(telefone.getId());
			}
		}
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

	@Override
	public Cliente adicionarContatoCliente(Cliente cliente) {
		Telefone telefone = new Telefone();
		telefone.setCliente(cliente);
		cliente.getListaTelefones().add(telefone);
		return cliente;
	}

	@Override
	public Cliente removeTelefoneCliente(Cliente cliente, int index) {
		Telefone excluirTelefone = cliente.getListaTelefones().get(index);
		if(excluirTelefone.getId() != null) {
			telefoneRepository.deleteById(excluirTelefone.getId());
		}	
		cliente.getListaTelefones().remove(index);
		return cliente;
	}

	@Override
	public void salvarTelefoneCliente(Cliente cliente) {
		if(cliente.getListaTelefones().size() != -1) {
			for(Telefone telefone : cliente.getListaTelefones()) {
				telefone.setCliente(cliente);
				telefoneRepository.save(telefone);
			}
			
		}
	}

	@Override
	public Cliente findClienteByIdAndTelefone(Long id) {
		return clienteRepository.findClienteByIdAndTelefone(id);
	}
	

}