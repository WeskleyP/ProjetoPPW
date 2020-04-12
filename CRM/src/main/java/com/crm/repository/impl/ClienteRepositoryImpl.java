package com.crm.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.crm.model.Cliente_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.crm.model.Cliente;
import com.crm.repository.filter.ClienteFilter;
import com.crm.repository.query.ClienteQuery;

public class ClienteRepositoryImpl implements ClienteQuery {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Cliente> listaClienteComPaginacao(ClienteFilter clienteFilter, Pageable pageable) {
		List<Cliente> listaCliente = new ArrayList<>();
		List<Predicate> listaPredicados = new ArrayList<>();
		
		TypedQuery<Cliente> query = null;
		
		int totalRegistroPorPagina = pageable.getPageSize();
		int pagAtual = pageable.getPageNumber();
		int firstRegistro = pagAtual*totalRegistroPorPagina;
		//SELECT * FROM CLIENTE;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder.createQuery(Cliente.class);
		
		Root<Cliente> rootFromCliente = criteriaQuery.from(Cliente.class);
		
		if(!StringUtils.isEmpty(clienteFilter.getNome())) {
			listaPredicados.add(criteriaBuilder.like(criteriaBuilder.lower(rootFromCliente.get(Cliente_.NOME)), "%"+clienteFilter.getNome()+"%"));
		}
		if (listaPredicados.size()!= -1) {
			criteriaQuery.where(criteriaBuilder.and(listaPredicados.toArray(new Predicate[listaPredicados.size()])));
			query = entityManager.createQuery(criteriaQuery);
		}else {
			query = entityManager.createQuery(criteriaQuery);
		}
		query.setFirstResult(firstRegistro);
		query.setMaxResults(totalRegistroPorPagina);
		
		listaCliente = query.getResultList();
		
		return new PageImpl<>(listaCliente,pageable, totalRegistroPesquisa(listaPredicados));
	}
	private Long totalRegistroPesquisa(List<Predicate> listaPredicates) {
		TypedQuery<Long> query = null;
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Cliente> rootFromCliente = criteriaQuery.from(Cliente.class);
		
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
	@Override
	public Optional<Cliente> findClienteByEmail(String email) {
		
		TypedQuery<Cliente> query = entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.email =:email", Cliente.class);
		return query.setParameter("email", email).
				setMaxResults(1).
				getResultList()
				.stream()
				.findFirst();
	}

}