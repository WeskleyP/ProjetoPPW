package com.crm.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Produto;
import com.crm.repository.filter.ProdutoFilter;

public interface ProdutoQuery {
	Page<Produto> listaProdutoComPaginacao(ProdutoFilter produtoFilter, Pageable pageable);
}
