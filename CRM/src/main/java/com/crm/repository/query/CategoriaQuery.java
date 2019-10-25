package com.crm.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Categoria;
import com.crm.repository.filter.CategoriaFilter;

public interface CategoriaQuery {
	Page<Categoria> listaCategoriaComPaginacao(CategoriaFilter categoriaFilter, Pageable pageable);
}
