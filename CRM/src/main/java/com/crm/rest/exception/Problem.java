package com.crm.rest.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Problem {

    private Integer status; //Código HTTP
    private LocalDateTime timestamp; //Data e hora que ocorreu o erro
    private String type; //Uma uri que especifica o tipo do problema
    private String title; //Descrição do tipo do problema mais legível possível
    private String detail; //Descrição sobre a ocorrência do erro
    private String userMessage; //Mensagem genérica para o usuário
    private List<Fields> fields;

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUserMessage() {
        return this.userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public List<Fields> getFields() {
        return this.fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Problem problem;
        
        public Builder(){
            this.problem = new Problem();
        }

        public Builder addStatus(Integer status){
            this.problem.status = status;
            return this; 
        }
        public Builder addTimestamp(LocalDateTime timestamp){
            this.problem.timestamp = timestamp;
            return this; 
        }
        public Builder addType(String type){
            this.problem.type = type;
            return this; 
        }
        public Builder addTitle(String title){
            this.problem.title = title;
            return this; 
        }
        public Builder addDetail(String detail){
            this.problem.detail = detail;
            return this; 
        }
        public Builder addUserMessage(String userMessage){
            this.problem.userMessage = userMessage;
            return this; 
        }
        public Builder addListFields(List<Fields> fields){
            this.problem.fields = fields;
            return this; 
        }
        public Problem build() {
            return this.problem;
        }
    } 
}