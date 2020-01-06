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

import com.crm.model.Categoria;
import com.crm.model.Categoria_;
import com.crm.model.ClientePessoaFisica;
import com.crm.model.ClientePessoaFisica_;
import com.crm.repository.filter.ClientePessoaFisicaFilter;
import com.crm.repository.query.ClientePessoaFisicaQuery;

public class ClientePessoaFisicaRepositoryImpl implements ClientePessoaFisicaQuery{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<ClientePessoaFisica> listaCategoriaComPaginacao(ClientePessoaFisicaFilter clientePessoaFisicaFilter,
			Pageable pageable) {
		List<ClientePessoaFisica> listaClientePessoaFisica= new ArrayList<>();
		List<Predicate> listaPredicados = new ArrayList<>();
		
		TypedQuery<ClientePessoaFisica> query = null;
		
		int totalRegistroPorPagina = pageable.getPageSize();
		int pagAtual = pageable.getPageNumber();
		int firstRegistro = pagAtual*totalRegistroPorPagina;
		//SELECT * FROM CLIENTE;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ClientePessoaFisica> criteriaQuery = criteriaBuilder.createQuery(ClientePessoaFisica.class);
		
		Root<ClientePessoaFisica> rootFromCliente = criteriaQuery.from(ClientePessoaFisica.class);
		
		if(!StringUtils.isEmpty(clientePessoaFisicaFilter.getNome())) {
			listaPredicados.add(criteriaBuilder.like(criteriaBuilder.lower(rootFromCliente.get(ClientePessoaFisica_.CLIENTE)), "%"+clientePessoaFisicaFilter.getNome()+"%"));
		}
		if (listaPredicados.size()!= -1) {
			criteriaQuery.where(criteriaBuilder.and(listaPredicados.toArray(new Predicate[listaPredicados.size()])));
			query = entityManager.createQuery(criteriaQuery);
		}else {
			query = entityManager.createQuery(criteriaQuery);
		}
		query.setFirstResult(firstRegistro);
		query.setMaxResults(totalRegistroPorPagina);
		
		listaClientePessoaFisica = query.getResultList();
		
		return new PageImpl<>(listaClientePessoaFisica,pageable, totalRegistroPesquisa(listaPredicados));
	}
	private Long totalRegistroPesquisa(List<Predicate> listaPredicates) {
		TypedQuery<Long> query = null;
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<ClientePessoaFisica> rootFromCliente = criteriaQuery.from(ClientePessoaFisica.class);
		
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
