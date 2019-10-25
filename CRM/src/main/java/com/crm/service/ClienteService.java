package com.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Cliente;
import com.crm.repository.filter.ClienteFilter;

public interface ClienteService {
	Cliente saveCliente(Cliente cliente);
	Cliente update(Cliente cliente);
	void remove(Cliente cliente);
	List<Cliente> findAll();
	Cliente findClienteById(Long id);
	List<Cliente> findClienteByName(String nome);
	Page<Cliente> listaClienteComPaginacao(ClienteFilter clienteFilter,Pageable pageable);
	Optional<Cliente> findClienteByEmail(String email);
}
