package com.crm.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ {

	public static volatile SingularAttribute<Cliente, ClientePessoaFisica> clientePessoaFisica;
	public static volatile ListAttribute<Cliente, Telefone> listaTelefones;
	public static volatile SingularAttribute<Cliente, Boolean> registroDeletado;
	public static volatile SingularAttribute<Cliente, String> nome;
	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile SingularAttribute<Cliente, String> email;

	public static final String CLIENTE_PESSOA_FISICA = "clientePessoaFisica";
	public static final String LISTA_TELEFONES = "listaTelefones";
	public static final String REGISTRO_DELETADO = "registroDeletado";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String EMAIL = "email";

}

