package com.crm.repository.impl;

import com.crm.model.Escopo;
import com.crm.model.Permissao;
import com.crm.model.Role;
import com.crm.model.RolePermissao;
import com.crm.repository.filter.RolePermissaoFiltro;
import com.crm.repository.query.RolePermissaoQuery;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class RolePermissaoRepositoryImpl implements RolePermissaoQuery {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RolePermissao> findRolePermissaoEscopoFiltro(RolePermissaoFiltro rolePermissaoFiltro) {
        TypedQuery<RolePermissao> query = null;

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RolePermissao> cq = cb.createQuery(RolePermissao.class);
        Root<RolePermissao> fromRolePermissao = cq.from(RolePermissao.class);

        if (!StringUtils.isEmpty(rolePermissaoFiltro.getRoleNome())) {
            Join<RolePermissao, Role> rolePermissao_role = fromRolePermissao.join("roleId");
            cq.where(cb.like(cb.lower(rolePermissao_role.get("nomeRole")), "%" + rolePermissaoFiltro.getRoleNome() + "%"));
        } else if (!StringUtils.isEmpty(rolePermissaoFiltro.getPermissaoNome())) {
            Join<RolePermissao, Permissao> rolePermissao_permissao = fromRolePermissao.join("permissaoId");
            cq.where(cb.like(cb.lower(rolePermissao_permissao.get("nome")), "%" + rolePermissaoFiltro.getPermissaoNome() + "%"));
        } else if (!StringUtils.isEmpty(rolePermissaoFiltro.getEscopoNome())) {
            Join<RolePermissao, Escopo> rolePermissao_escopo = fromRolePermissao.join("escopoId");
            cq.where(cb.like(cb.lower(rolePermissao_escopo.get("nome")), "%" + rolePermissaoFiltro.getEscopoNome() + "%"));
        } else {
            cq.select(fromRolePermissao);
        }

        query = entityManager.createQuery(cq);

        return query.getResultList();
    }
}