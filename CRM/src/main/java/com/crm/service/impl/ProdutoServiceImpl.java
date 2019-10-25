package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Produto;
import com.crm.model.Telefone;
import com.crm.repository.ProdutoRepository;
import com.crm.repository.filter.ProdutoFilter;
import com.crm.service.ProdutoService;

@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;

	@Override
	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public Produto update(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public void remove(Produto produto) {
		Produto produtoEncontrado = findProdutoById(produto.getId());
		produtoRepository.delete(produtoEncontrado);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Produto findProdutoById(Long id) {
		return produtoRepository.getOne(id);
	}

	@Override
	public List<Produto> findProdutoByDesc(String descricao) {
		return produtoRepository.findProdutoByDesc(descricao);
	}

	@Override
	public Page<Produto> listaProdutoComPaginacao(ProdutoFilter produtoFilter, Pageable pageable) {
		return produtoRepository.listaProdutoComPaginacao(produtoFilter, pageable);
	}

}
