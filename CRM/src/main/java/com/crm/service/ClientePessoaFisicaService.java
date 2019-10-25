package com.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.ClientePessoaFisica;
import com.crm.repository.filter.ClientePessoaFisicaFilter;

public interface ClientePessoaFisicaService {
	ClientePessoaFisica saveClientePessoaFisica(ClientePessoaFisica clientePessoaFisica);
	ClientePessoaFisica update(ClientePessoaFisica clientePessoaFisica);
	void remove(ClientePessoaFisica clientePessoaFisica);
	List<ClientePessoaFisica> findAll();
	ClientePessoaFisica findClientePessoaFisicaById(Long id);
	Page<ClientePessoaFisica> listaClientePessoaFisicaComPaginacao(ClientePessoaFisicaFilter clientePessoaFisicaFilter,Pageable pageable);
//	Optional<ClientePessoaFisica> findClientePessoaFisicaByEmail(String email);
}
