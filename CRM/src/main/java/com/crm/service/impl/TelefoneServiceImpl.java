package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.model.Telefone;
import com.crm.repository.TelefoneRepository;
import com.crm.service.TelefoneService;

@Service
@Transactional
public class TelefoneServiceImpl implements TelefoneService {
	@Autowired
	private TelefoneRepository telefoneRepository;
	@Override
	public Telefone saveTelefone(Telefone telefone) {
		return telefoneRepository.save(telefone);
	}

	@Override
	public Telefone update(Telefone telefone) {
		return telefoneRepository.save(telefone);
	}

	@Override
	public void remove(Telefone telefone) {
		Telefone telefoneEncontrado = findTelefoneById(telefone.getId());
		telefoneRepository.delete(telefoneEncontrado);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Telefone> findAll() {
		return telefoneRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Telefone findTelefoneById(Long id) {
		return telefoneRepository.getOne(id);
	}

}
