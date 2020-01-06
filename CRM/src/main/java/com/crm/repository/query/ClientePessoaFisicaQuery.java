package com.crm.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.ClientePessoaFisica;
import com.crm.repository.filter.ClientePessoaFisicaFilter;

public interface ClientePessoaFisicaQuery {
	Page<ClientePessoaFisica> listaCategoriaComPaginacao(ClientePessoaFisicaFilter clientePessoaFisicaFilter, Pageable pageable);
}
