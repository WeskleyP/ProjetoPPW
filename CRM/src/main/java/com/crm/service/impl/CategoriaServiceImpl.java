package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Categoria;
import com.crm.model.Cliente;
import com.crm.repository.CategoriaRepository;
import com.crm.repository.filter.CategoriaFilter;
import com.crm.service.CategoriaService;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Override
	public Categoria saveCategoria(Categoria categoria) {
		System.out.println(categoria.toString());
		return categoriaRepository.save(categoria);
	}

	@Override
	public Categoria update(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	public void remove(Categoria categoria) throws Exception {
		Categoria categoriaEncontrada = findCategoriaById(categoria.getId());
		if(categoriaEncontrada.getListaProdutos().size() > 0) {
			throw new Exception();
		}
		categoriaRepository.delete(categoriaEncontrada);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria findCategoriaById(Long id) {
		return categoriaRepository.getOne(id);
	}

	@Override
	public Page<Categoria> listaCategoriaComPaginacao(CategoriaFilter categoriaFilter, Pageable pageable) {
		return categoriaRepository.listaCategoriaComPaginacao(categoriaFilter, pageable);
	}

	@Override
	public List<Cliente> findCategoriaByName(String nome) {
		return categoriaRepository.findCategoriaByName(nome);
	}

}
