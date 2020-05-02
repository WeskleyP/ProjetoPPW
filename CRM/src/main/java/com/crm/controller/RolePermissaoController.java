package com.crm.controller;

import com.crm.model.*;
import com.crm.repository.filter.RolePermissaoFiltro;
import com.crm.service.EscopoService;
import com.crm.service.PermissaoService;
import com.crm.service.RolePermissaoService;
import com.crm.service.RoleService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping(value = "/direitos")
public class RolePermissaoController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissaoService permissaoService;
    @Autowired
    private EscopoService escopoService;
    @Autowired
    private RolePermissaoService rolePermissaoService;

    @RequestMapping(value = "/list")
    public ModelAndView listarRolePermissoes(RolePermissaoFiltro rolePermissaoFiltro) {
        ModelAndView mv = new ModelAndView("rolepermissao/listarolepermissao");
        List<RolePermissao> listaRolePermissao = rolePermissaoService.findRolePermissaoEscopoFiltro(rolePermissaoFiltro);
        mv.addObject("listaRolePermissao", listaRolePermissao);
        return mv;
    }

    @RequestMapping(value = "/novo")
    public ModelAndView cadastroRolePermissao(RolePermissao rolePermissao){
        ModelAndView mv = new ModelAndView("rolepermissao/cadastrorolepermissao");
        mv.addObject("rolePermissao",rolePermissao);
        return mv;
    }
    @RequestMapping(value = "/incluir", method =  RequestMethod.POST)
    public ModelAndView incluirRolePermissao(RolePermissao rolePermissao){
        RolePermissaoId id = new RolePermissaoId();
        id.setRoleId(rolePermissao.getRoleId().getIdRole());
        id.setPermissaoId(rolePermissao.getPermissaoId().getId());
        id.setEscopoId(rolePermissao.getEscopoId().getId());
        rolePermissao.setId(id);
        rolePermissaoService.save(rolePermissao);
        return new ModelAndView("redirect:/direitos/list");
    }

    @RequestMapping(value = "/excluir/{role_id}/{permissao_id}/{escopo_id}")
    public ModelAndView excluirCadastroRolePermissao(@PathVariable Long role_id,
                                                     @PathVariable Long permissao_id,
                                                     @PathVariable Long escopo_id){
        RolePermissaoId id = new RolePermissaoId();
        id.setRoleId(role_id);
        id.setPermissaoId(permissao_id);
        id.setEscopoId(escopo_id);
        RolePermissao rolePermissao = rolePermissaoService.getOne(id);

        ModelAndView mv = new ModelAndView("rolepermissao/excluirrolepermissao");
        mv.addObject("rolePermissao", rolePermissao);
        return mv;
    }

    @ModelAttribute("roles")
    public List<Role> listarRoles(){
        return roleService.findAll();
    }
    @ModelAttribute("permissoes")
    public List<Permissao> listarPermissoes(){
        return permissaoService.findAll();
    }
    @ModelAttribute("escopos")
    public List<Escopo> listarEscopos(){
        return escopoService.findAll();
    }
}
