package com.crm.service.exceptions;

public class RoleNaoCadastradaException extends EntidadeNaoCadastradaException {

    private static final long serialVersionUID = 1L;

    public RoleNaoCadastradaException(String mensagem) {
        super(mensagem);
    }
    public RoleNaoCadastradaException(long id){
        this(String.format("Não existe um cadastrado da Role com o código %d", id));
    }

}