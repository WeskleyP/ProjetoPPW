package com.crm.rest.exception;

public enum ProblemType{

    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "O recurso solicitado não foi localizado"),
    ENTIDADE_EM_USO("/entidade-em-uso","A entidade está em uso no sistema"), 
    DADOS_INVALIDOS("/dados-invalidos","Dados inválidos");

    private String uri;
    private String title;

    private ProblemType(String uri, String title) {
        this.uri = uri;
        this.title = title;
    }

    public String getUri(){
        return this.uri;
    }
    public String getTitle(){
        return this.title;
    }
}