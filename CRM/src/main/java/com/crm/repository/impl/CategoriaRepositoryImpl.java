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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;

import com.crm.model.Categoria;
import com.crm.model.Categoria_;
import com.crm.model.Cliente;
import com.crm.model.Cliente_;
import com.crm.repository.filter.CategoriaFilter;
import com.crm.repository.filter.ClienteFilter;
import com.crm.repository.query.CategoriaQuery;

public class CategoriaRepositoryImpl implements CategoriaQuery{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Categoria> listaCategoriaComPaginacao(CategoriaFilter categoriaFilter, Pageable pageable) {
		List<Categoria> listaCategoria= new ArrayList<>();
		List<Predicate> listaPredicados = new ArrayList<>();
		
		TypedQuery<Categoria> query = null;
		
		int totalRegistroPorPagina = pageable.getPageSize();
		int pagAtual = pageable.getPageNumber();
		int firstRegistro = pagAtual*totalRegistroPorPagina;
		//SELECT * FROM CLIENTE;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Categoria> criteriaQuery = criteriaBuilder.createQuery(Categoria.class);
		
		Root<Categoria> rootFromCliente = criteriaQuery.from(Categoria.class);
		
		if(!StringUtils.isEmpty(categoriaFilter.getNome())) {
			listaPredicados.add(criteriaBuilder.like(criteriaBuilder.lower(rootFromCliente.get(Categoria_.NOME)), "%"+categoriaFilter.getNome()+"%"));
		}
		if (listaPredicados.size()!= -1) {
			criteriaQuery.where(criteriaBuilder.and(listaPredicados.toArray(new Predicate[listaPredicados.size()])));
			query = entityManager.createQuery(criteriaQuery);
		}else {
			query = entityManager.createQuery(criteriaQuery);
		}
		query.setFirstResult(firstRegistro);
		query.setMaxResults(totalRegistroPorPagina);
		
		listaCategoria = query.getResultList();
		
		return new PageImpl<>(listaCategoria,pageable, totalRegistroPesquisa(listaPredicados));
	}
	private Long totalRegistroPesquisa(List<Predicate> listaPredicates) {
		TypedQuery<Long> query = null;
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Categoria> rootFromCliente = criteriaQuery.from(Categoria.class);
		
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
