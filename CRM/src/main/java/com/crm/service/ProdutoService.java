package com.crm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Produto;
import com.crm.repository.filter.ProdutoFilter;

public interface ProdutoService {
	Produto saveProduto(Produto produto);
	Produto update(Produto produto);
	void remove(Produto produto) throws Exception;
	List<Produto> findAll();
	Produto findProdutoById(Long id);
	List<Produto> findProdutoByDesc(String desc);
	Page<Produto> listaProdutoComPaginacao(ProdutoFilter produtoFilter,Pageable pageable);
}
