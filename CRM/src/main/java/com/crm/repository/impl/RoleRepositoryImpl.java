package com.crm.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.crm.model.Role;
import com.crm.model.Role_;
import com.crm.repository.filter.RoleFilter;
import com.crm.repository.query.RoleQuery;

public class RoleRepositoryImpl implements RoleQuery{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Role> listaRoleComPaginacao(RoleFilter roleFilter, Pageable pageable) {
		List<Role> listaRole = new ArrayList<>();
		List<Predicate> listaPredicados = new ArrayList<>();
		
		TypedQuery<Role> query = null;
		
		int totalRegistroPorPagina = pageable.getPageSize();
		int pagAtual = pageable.getPageNumber();
		int firstRegistro = pagAtual*totalRegistroPorPagina;
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
		
		Root<Role> rootFromCliente = criteriaQuery.from(Role.class);
		
		if(!StringUtils.isEmpty(roleFilter.getNomeRole())) {
			listaPredicados.add(criteriaBuilder.like(criteriaBuilder.lower(rootFromCliente.get(Role_.NOME_ROLE)), "%"+roleFilter.getNomeRole()+"%"));
		}
		if (listaPredicados.size()!= -1) {
			criteriaQuery.where(criteriaBuilder.and(listaPredicados.toArray(new Predicate[listaPredicados.size()])));
			query = entityManager.createQuery(criteriaQuery);
		}else {
			query = entityManager.createQuery(criteriaQuery);
		}
		query.setFirstResult(firstRegistro);
		query.setMaxResults(totalRegistroPorPagina);
		
		listaRole = query.getResultList();
		
		return new PageImpl<>(listaRole,pageable, totalRegistroPesquisa(listaPredicados));
	}
	private Long totalRegistroPesquisa(List<Predicate> listaPredicates) {
		TypedQuery<Long> query = null;
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Role> rootFromCliente = criteriaQuery.from(Role.class);
		
		criteriaQuery.select(criteriaBuilder.count(rootFromCliente));
		
		if (listaPredicates.size()!= -1) {
			criteriaQuery.where(criteriaBuilder.and(listaPredicates.toArray(new Predicate[listaPredicates.size()])));
			query = entityManager.createQuery(criteriaQuery);
		}else {
			query = entityManager.createQuery(criteriaQuery);
		}
		Long result = query.getSingleResult();
		return result;
	}
	
}
