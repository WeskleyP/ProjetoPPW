package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.model.ClientePessoaFisica;
import com.crm.repository.query.ClientePessoaFisicaQuery;
@Repository
public interface ClientePessoaFisicaRepository extends JpaRepository<ClientePessoaFisica, Long>, ClientePessoaFisicaQuery{

}
