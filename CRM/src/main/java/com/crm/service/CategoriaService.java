package com.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Categoria;
import com.crm.model.Cliente;
import com.crm.repository.filter.CategoriaFilter;
import com.crm.repository.filter.ClienteFilter;

public interface CategoriaService {
	Categoria saveCategoria(Categoria categoria);
	Categoria update(Categoria categoria);
	void remove(Categoria categoria);
	List<Categoria> findAll();
	Categoria findCategoriaById(Long id);
	List<Cliente> findCategoriaByName(String nome);
	Page<Categoria> listaCategoriaComPaginacao(CategoriaFilter categoriaFilter,Pageable pageable);
}
