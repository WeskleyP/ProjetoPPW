package com.crm.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.crm.model.Role;
import com.crm.rest.exception.Problem;
import com.crm.rest.exception.ProblemType;
import com.crm.service.RoleService;
import com.crm.service.exceptions.NegocioException;
import com.crm.service.exceptions.RoleNaoCadastradaException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@RestController
@RequestMapping(value = "/roles")
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/lista")
    public List<Role> listaRoles() {
        return roleService.findAll();
    }
    @GetMapping(value = "/buscar/{id}")
    public ResponseEntity<Role> findRole(@PathVariable Long id){
        Role roleCadastrada = roleService.findRoleById(id);
        if(roleCadastrada != null){
            return ResponseEntity.ok(roleCadastrada);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role saveNewRole(@RequestBody Role role){
        return roleService.saveRole(role);
    }
    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role){
        try {
        Role roleCadastrada = roleService.findRoleById(id);
            if(roleCadastrada != null){
                BeanUtils.copyProperties(role, roleCadastrada, "idRole");
                roleCadastrada = roleService.update(roleCadastrada);
                return ResponseEntity.accepted().body(roleCadastrada);
            }
        } catch (RoleNaoCadastradaException e) {
            throw new NegocioException("A role não existe no sistema");
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(value = "/excluir/{id}")
    public ResponseEntity<?> removeRole(@PathVariable Long id){
        Role roleCadastrada = roleService.findRoleById(id);
        roleService.remove(roleCadastrada);
        return ResponseEntity.noContent().build();
    }

}