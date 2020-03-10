package com.crm.model.dto;

import javax.validation.constraints.NotNull;

import com.crm.model.Usuario;

public class UserDTO {
	private Long idUsuario;
	private String username;
	private String emailUsuario;
    private boolean ativo;
    
    public UserDTO(){

    }
    public UserDTO(Usuario user){
        this.idUsuario = user.getIdUsuario();
        this.username = user.getUsername();
        this.emailUsuario = user.getEmailUsuario();
        this.ativo = user.isAtivo();
    }
    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailUsuario() {
        return this.emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
