package com.crm.repository.query;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Cliente;
import com.crm.repository.filter.ClienteFilter;

public interface ClienteQuery {
	Page<Cliente> listaClienteComPaginacao(ClienteFilter clienteFilter, Pageable pageable);
	Optional<Cliente> findClienteByEmail(String email);
}
