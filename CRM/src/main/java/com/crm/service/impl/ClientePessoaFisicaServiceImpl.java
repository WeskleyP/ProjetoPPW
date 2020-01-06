package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.ClientePessoaFisica;
import com.crm.repository.ClientePessoaFisicaRepository;
import com.crm.repository.ClienteRepository;
import com.crm.repository.filter.ClientePessoaFisicaFilter;
import com.crm.service.ClientePessoaFisicaService;

@Service
@Transactional
public class ClientePessoaFisicaServiceImpl implements ClientePessoaFisicaService {
	@Autowired
	private ClientePessoaFisicaRepository clientePessoaFisicaRepository;
	@Override
	public ClientePessoaFisica saveClientePessoaFisica(ClientePessoaFisica clientePessoaFisica) {
		return clientePessoaFisicaRepository.save(clientePessoaFisica);
	}

	@Override
	public ClientePessoaFisica update(ClientePessoaFisica clientePessoaFisica) {
		return clientePessoaFisicaRepository.save(clientePessoaFisica);
	}

	@Override
	public void remove(ClientePessoaFisica clientePessoaFisica) {
		ClientePessoaFisica clientePessoaFisicaEncontrada = findClientePessoaFisicaById(clientePessoaFisica.getId());
		clientePessoaFisicaRepository.delete(clientePessoaFisicaEncontrada);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ClientePessoaFisica> findAll() {
		return clientePessoaFisicaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ClientePessoaFisica findClientePessoaFisicaById(Long id) {
		return clientePessoaFisicaRepository.getOne(id);
	}

	@Override
	public Page<ClientePessoaFisica> listaClientePessoaFisicaComPaginacao(
		ClientePessoaFisicaFilter clientePessoaFisicaFilter, Pageable pageable) {
		return clientePessoaFisicaRepository.listaCategoriaComPaginacao(clientePessoaFisicaFilter, pageable);
	}
	

}
