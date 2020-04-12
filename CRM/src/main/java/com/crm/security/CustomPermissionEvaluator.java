package com.crm.security;

import java.io.Serializable;

import com.crm.service.UsuarioService;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private UsuarioService usuarioService;

    public CustomPermissionEvaluator(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }
    @Override
    public boolean hasPermission(Authentication usuarioLogado, Object atividade, Object escopo) {
        hasPrivillege(usuarioLogado, atividade, escopo);
        return false;
    }

    @Override
    public boolean hasPermission(Authentication usuarioLogado, Serializable targetId, String targetType,
            Object escopo) {
        return false;
    }

    private boolean hasPrivillege(Authentication usuarioLogado, Object atividade, Object escopo){
        return false;
    }
}