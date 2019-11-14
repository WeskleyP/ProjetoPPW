package com.crm.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crm.config.CrmConfig;
import com.crm.controller.page.PageWrapper;
import com.crm.model.Role;
import com.crm.repository.filter.RoleFilter;
import com.crm.service.RoleService;
import com.crm.service.exceptions.EmailClienteExistente;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@RequestMapping(value = "/list_role", method = RequestMethod.GET)
	public ModelAndView listRole(RoleFilter roleFilter, HttpServletRequest httpServletRequest,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "page", required = false) Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(CrmConfig.INITIAL_PAGE),
				size.orElse(CrmConfig.INITIAL_PAGE_SIZE));
		PageWrapper<Role> pagina = new PageWrapper<>(
				roleService.listaRoleComPaginacao(roleFilter, pageable),
				size.orElse(CrmConfig.INITIAL_PAGE_SIZE), httpServletRequest);
		ModelAndView mv = new ModelAndView("/role/listar_role");
		mv.addObject("pageSizes", CrmConfig.PAGE_SIZES);
		mv.addObject("size", size.orElse(CrmConfig.INITIAL_PAGE_SIZE));
		mv.addObject("pagina", pagina);
		return mv;
	}

	/*
	 * Rotinas para inclusão e alteração de registro
	 */
	@RequestMapping(value = "/novo_role", method = RequestMethod.GET)
	public ModelAndView newForm(Role role) {
		ModelAndView mv = new ModelAndView("/role/role");
		mv.addObject("role", role);
		return mv;
	}

	@RequestMapping(value = "/salvar_role", method = RequestMethod.POST)
	public ModelAndView saveNewRole(@Valid Role role, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return newForm(role);
		}
		try {
			roleService.saveRole(role);
		} catch (EmailClienteExistente e) {
			result.rejectValue("email", e.getMessage());
			return newForm(role);
		}
		attr.addFlashAttribute("success", "Registro Cadastrado com sucesso.");
		return new ModelAndView("redirect:/role/novo_role");
	}

	@RequestMapping(value = "/editar_role", method = RequestMethod.POST)
	public ModelAndView editRole(@Valid Role role, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return newForm(role);
		}
		roleService.update(role);
		attr.addFlashAttribute("success", "Registro Editado com sucesso.");
		return new ModelAndView("redirect:/role/novo_role");
	}

	@RequestMapping(value = "/buscar_role/{id}", method = RequestMethod.GET)
	public ModelAndView searchRole(@PathVariable("id") Long id) {

		Role role = new Role();
		role = roleService.findRoleById(id);
		return newForm(role);
	}

	@RequestMapping(value = "/consulta_role/{id}", method = RequestMethod.GET)
	public ModelAndView consultaRoleByID(@PathVariable("id") Long id) {
		Role role = new Role();
		role = roleService.findRoleById(id);
		ModelAndView mv = new ModelAndView("/role/consulta_role");
		mv.addObject("role", role);
		return mv;
	}

	/*
	 * Rotinas para exclusão de registro
	 */
	@RequestMapping(value = "/excluir_role/{id}", method = RequestMethod.GET)
	public ModelAndView removeRoleById(@PathVariable("id") Long id) {
		Role role = new Role();
		role = roleService.findRoleById(id);
		ModelAndView mv = new ModelAndView("/role/excluir_role");
		mv.addObject("role", role);
		return mv;
	}

	@RequestMapping(value = "/excluir_cliente", method = RequestMethod.POST)
	public ModelAndView removeRole(Role role, RedirectAttributes attr) {
		roleService.remove(role);
		attr.addFlashAttribute("success", "Registro Excluido com sucesso.");
		return new ModelAndView("redirect:/role/novo_role");
	}
	@RequestMapping(value= {"/salvar_role","/editar_role","/excluir_role"}, method=RequestMethod.POST, params="action=cancelar")
	public String cancelar() {
		return "redirect:/role/list_role";
	}

}
