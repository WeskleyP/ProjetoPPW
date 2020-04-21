package com.crm.service.impl;

import com.crm.model.RolePermissao;
import com.crm.model.RolePermissaoId;
import com.crm.repository.RolePermissaoRepository;
import com.crm.service.RolePermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolePermissaoServiceImpl implements RolePermissaoService {

    @Autowired
    private RolePermissaoRepository rolePermissaoRepository;
    @Override
    public List<RolePermissao> findAll() {
        return rolePermissaoRepository.findAll();
    }

    @Override
    public RolePermissao save(RolePermissao entity) {
        return rolePermissaoRepository.save(entity);
    }

    @Override
    public RolePermissao update(RolePermissao entity) {
        return this.save(entity);
    }

    @Override
    public RolePermissao getOne(RolePermissaoId id) {
        return rolePermissaoRepository.getOne(id);
    }

    @Override
    public RolePermissao findById(RolePermissaoId id) {
        return rolePermissaoRepository.findById(id).get();
    }

    @Override
    public void deleteById(RolePermissaoId id) {
        rolePermissaoRepository.deleteById(id);
    }
}
