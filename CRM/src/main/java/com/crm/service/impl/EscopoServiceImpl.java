package com.crm.service.impl;


import com.crm.model.Escopo;
import com.crm.model.Role;
import com.crm.repository.EscopoRepository;
import com.crm.repository.RoleRepository;
import com.crm.repository.filter.RoleFilter;
import com.crm.service.EscopoService;
import com.crm.service.RoleService;
import com.crm.service.exceptions.EntidadeEmUsoException;
import com.crm.service.exceptions.RoleNaoCadastradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EscopoServiceImpl implements EscopoService {

	@Autowired
	private EscopoRepository escopoRepository;

	@Override
	public List<Escopo> findAll() {
		return escopoRepository.findAll();
	}

	@Override
	public Escopo save(Escopo entity) {
		return escopoRepository.save(entity);
	}

	@Override
	public Escopo update(Escopo entity) {
		return this.save(entity);
	}

	@Override
	public Escopo getOne(Long id) {
		return escopoRepository.getOne(id);
	}

	@Override
	public Escopo findById(Long id) {
		return escopoRepository.getOne(id);
	}

	@Override
	public void deleteById(Long id) {
		escopoRepository.deleteById(id);
	}

}
