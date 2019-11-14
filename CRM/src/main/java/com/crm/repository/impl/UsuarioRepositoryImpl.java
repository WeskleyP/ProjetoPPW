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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.crm.model.Usuario;
import com.crm.model.Usuario_;
import com.crm.repository.filter.UsuarioFilter;
import com.crm.repository.query.UsuarioQuery;

public class UsuarioRepositoryImpl implements UsuarioQuery{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Usuario> listaUsuarioComPaginacao(UsuarioFilter usuarioFilter, Pageable pageable) {
		List<Usuario> listaUsuario = new ArrayList<>();
		List<Predicate> listaPredicados = new ArrayList<>();
		
		TypedQuery<Usuario> query = null;
		
		int totalRegistroPorPagina = pageable.getPageSize();
		int pagAtual = pageable.getPageNumber();
		int firstRegistro = pagAtual*totalRegistroPorPagina;
		//SELECT * FROM Usuario;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		
		Root<Usuario> rootFromCliente = criteriaQuery.from(Usuario.class);
		
		if(!StringUtils.isEmpty(usuarioFilter.getusername())) {
			listaPredicados.add(criteriaBuilder.like(criteriaBuilder.lower(rootFromCliente.get(Usuario_. USERNAME)), "%"+usuarioFilter.getusername()+"%"));
		}
		if (listaPredicados.size()!= -1) {
			criteriaQuery.where(criteriaBuilder.and(listaPredicados.toArray(new Predicate[listaPredicados.size()])));
			query = entityManager.createQuery(criteriaQuery);
		}else {
			query = entityManager.createQuery(criteriaQuery);
		}
		query.setFirstResult(firstRegistro);
		query.setMaxResults(totalRegistroPorPagina);
		
		listaUsuario = query.getResultList();
		
		return new PageImpl<>(listaUsuario,pageable, totalRegistroPesquisa(listaPredicados));
	}
	private Long totalRegistroPesquisa(List<Predicate> listaPredicates) {
		TypedQuery<Long> query = null;
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Usuario> rootFromUsuario = criteriaQuery.from(Usuario.class);
		
		criteriaQuery.select(criteriaBuilder.count(rootFromUsuario));
		
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
	public Optional<Usuario> findUsuarioByEmail(String emailUsuario) {
		boolean ativo = true;
		
		TypedQuery<Usuario> query = entityManager
				.createQuery("SELECT u FROM Usuario u "
							+ "WHERE u.emailUsuario =: emailUsuario "
							+ "and "
							+ "u.ativo =: ativo"
							, Usuario.class);
		return query.setParameter("emailUsuario", emailUsuario)
				.setParameter("ativo", ativo)
				.setMaxResults(1)
				.getResultList()
				.stream()
				.findFirst();
	}
}
