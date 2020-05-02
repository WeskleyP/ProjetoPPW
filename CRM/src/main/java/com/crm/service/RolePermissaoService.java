package com.crm.service;

import com.crm.model.RolePermissao;
import com.crm.model.RolePermissaoId;
import com.crm.repository.filter.RolePermissaoFiltro;

import java.util.List;

public interface RolePermissaoService extends GenericService<RolePermissao, RolePermissaoId> {

    List<RolePermissao> findRolePermissaoEscopoFiltro(RolePermissaoFiltro rolePermissaoFiltro);
}
