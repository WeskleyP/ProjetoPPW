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
import com.crm.model.Produto;
import com.crm.model.Produto_;
import com.crm.repository.filter.ProdutoFilter;
import com.crm.repository.query.ProdutoQuery;

public class ProdutoRepositoryImpl implements ProdutoQuery{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Page<Produto> listaProdutoComPaginacao(ProdutoFilter produtoFilter, Pageable pageable) {
		List<Produto> listaProduto= new ArrayList<>();
		List<Predicate> listaPredicados = new ArrayList<>();
		
		TypedQuery<Produto> query = null;
		
		int totalRegistroPorPagina = pageable.getPageSize();
		int pagAtual = pageable.getPageNumber();
		int firstRegistro = pagAtual*totalRegistroPorPagina;
		//SELECT * FROM CLIENTE;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
		
		Root<Produto> rootFromProduto = criteriaQuery.from(Produto.class);
		
		if(!StringUtils.isEmpty(produtoFilter.getDescricao())){
			listaPredicados.add(criteriaBuilder.like(criteriaBuilder.lower(rootFromProduto.get(Produto_.DESCRICAO)), "%"+produtoFilter.getDescricao()+"%"));
		}
		if (listaPredicados.size()!= -1) {
			criteriaQuery.where(criteriaBuilder.and(listaPredicados.toArray(new Predicate[listaPredicados.size()])));
			query = entityManager.createQuery(criteriaQuery);
		}else {
			query = entityManager.createQuery(criteriaQuery);
		}
		query.setFirstResult(firstRegistro);
		query.setMaxResults(totalRegistroPorPagina);
		
		listaProduto = query.getResultList();
		
		return new PageImpl<>(listaProduto,pageable, totalRegistroPesquisa(listaPredicados));
	}
	private Long totalRegistroPesquisa(List<Predicate> listaPredicates) {
		TypedQuery<Long> query = null;
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Produto> rootFromProduto= criteriaQuery.from(Produto.class);
		
		criteriaQuery.select(criteriaBuilder.count(rootFromProduto));
		
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
