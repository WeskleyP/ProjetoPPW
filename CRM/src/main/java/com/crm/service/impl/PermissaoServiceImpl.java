package com.crm.service.impl;


import com.crm.model.Escopo;
import com.crm.model.Permissao;
import com.crm.model.Role;
import com.crm.repository.PermissaoRepository;
import com.crm.repository.RoleRepository;
import com.crm.repository.filter.RoleFilter;
import com.crm.service.EscopoService;
import com.crm.service.PermissaoService;
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
public class PermissaoServiceImpl implements PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Override
    public List<Permissao> findAll() {
        return permissaoRepository.findAll();
    }

    @Override
    public Permissao save(Permissao entity) {
        return permissaoRepository.save(entity);
    }

    @Override
    public Permissao update(Permissao entity) {
        return this.save(entity);
    }

    @Override
    public Permissao getOne(Long id) {
        return permissaoRepository.getOne(id);
    }

    @Override
    public Permissao findById(Long id) {
        return permissaoRepository.getOne(id);
    }

    @Override
    public void deleteById(Long id) {
        permissaoRepository.deleteById(id);
    }
}