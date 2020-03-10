package com.crm.rest;

import java.util.List;
import java.util.stream.Collectors;

import com.crm.model.Usuario;
import com.crm.model.dto.OneUserDTO;
import com.crm.model.dto.UserDTO;
import com.crm.service.UsuarioService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/lista")
    public List<UserDTO> listUsers() {
        List<Usuario> obj = usuarioService.findAll();
        List<UserDTO> dtos = obj.stream().map(list -> new UserDTO(list)).collect(Collectors.toList());
        return dtos;
    }

    @GetMapping(value = "/buscar/{id}")
    public ResponseEntity<OneUserDTO> findUser(@PathVariable Long id) {
        Usuario obj = usuarioService.findUsuarioById(id);
        OneUserDTO dtos = new OneUserDTO(obj);
        if (dtos != null) {
            return ResponseEntity.ok(dtos);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public OneUserDTO saveNewUser(@RequestBody Usuario usuario) {
        try {
            usuarioService.saveUsuario(usuario);
            OneUserDTO obj = new OneUserDTO(usuario);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @PutMapping(value = "/atualizar/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody UserDTO usuario){
        Usuario user = usuarioService.fromDto(usuario);
        user.setIdUsuario(id);
        user = usuarioService.update(user);
        return ResponseEntity.accepted().build();
    }
    @DeleteMapping(value = "/excluir/{id}")
    public ResponseEntity<?> removeRole(@PathVariable Long id){
        Usuario usuarioCadastrado = usuarioService.findUsuarioById(id);
        usuarioService.remove(usuarioCadastrado);
        return ResponseEntity.noContent().build();
    }
}