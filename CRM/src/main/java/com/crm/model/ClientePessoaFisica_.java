package com.crm.model;

import com.crm.model.enumarate.Sexo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ClientePessoaFisica.class)
public abstract class ClientePessoaFisica_ {

	public static volatile SingularAttribute<ClientePessoaFisica, Cliente> cliente;
	public static volatile SingularAttribute<ClientePessoaFisica, String> cpf;
	public static volatile SingularAttribute<ClientePessoaFisica, Date> dateNasc;
	public static volatile SingularAttribute<ClientePessoaFisica, String> nomePai;
	public static volatile SingularAttribute<ClientePessoaFisica, Long> id;
	public static volatile SingularAttribute<ClientePessoaFisica, Sexo> sexo;
	public static volatile SingularAttribute<ClientePessoaFisica, String> nomeMae;

	public static final String CLIENTE = "cliente";
	public static final String CPF = "cpf";
	public static final String DATE_NASC = "dateNasc";
	public static final String NOME_PAI = "nomePai";
	public static final String ID = "id";
	public static final String SEXO = "sexo";
	public static final String NOME_MAE = "nomeMae";

}

