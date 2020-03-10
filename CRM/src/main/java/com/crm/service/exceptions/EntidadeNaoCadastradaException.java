package com.crm.service.exceptions;

public class EntidadeNaoCadastradaException extends NegocioException{

    private static final long serialVersionUID = 1L;
    
    public EntidadeNaoCadastradaException(String mensagem){
        super(mensagem);
    }
}