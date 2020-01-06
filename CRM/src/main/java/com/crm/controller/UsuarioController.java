package com.crm.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crm.config.CrmConfig;
import com.crm.controller.page.PageWrapper;
import com.crm.model.Categoria;
import com.crm.model.Role;
import com.crm.model.Usuario;
import com.crm.repository.filter.UsuarioFilter;
import com.crm.service.RoleService;
import com.crm.service.UsuarioService;
import com.crm.service.exceptions.EmailClienteExistente;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RoleService roleService;
	@RequestMapping(value = "/list_usuario", method = RequestMethod.GET)
	public ModelAndView listUsuario(UsuarioFilter usuarioFilter, HttpServletRequest httpServletRequest,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "page", required = false) Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(CrmConfig.INITIAL_PAGE),
				size.orElse(CrmConfig.INITIAL_PAGE_SIZE));
		PageWrapper<Usuario> pagina = new PageWrapper<>(
				usuarioService.listaUsuarioComPaginacao(usuarioFilter, pageable),
				size.orElse(CrmConfig.INITIAL_PAGE_SIZE), httpServletRequest);
		ModelAndView mv = new ModelAndView("/usuario/listar_usuario");
		mv.addObject("pageSizes", CrmConfig.PAGE_SIZES);
		mv.addObject("size", size.orElse(CrmConfig.INITIAL_PAGE_SIZE));
		mv.addObject("pagina", pagina);
		return mv;
	}

	/*
	 * Rotinas para inclusão e alteração de registro
	 */
	@RequestMapping(value = "/novo_usuario", method = RequestMethod.GET)
	public ModelAndView newForm(Usuario usuario) {
		ModelAndView mv = new ModelAndView("/usuario/usuario");
		mv.addObject("usuario", usuario);
		return mv;
	}

	@RequestMapping(value = "/salvar_usuario", method = RequestMethod.POST)
	public ModelAndView saveNewUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) throws Exception {
		if (result.hasErrors()) {
			return newForm(usuario);
		}
		try {
			usuarioService.saveUsuario(usuario);
		} catch (EmailClienteExistente e) {
			result.rejectValue("email", e.getMessage());
			return newForm(usuario);
		}
		attr.addFlashAttribute("success", "Registro Cadastrado com sucesso.");
		return new ModelAndView("redirect:/usuario/novo_usuario");
	}

	@RequestMapping(value = "/editar_usuario", method = RequestMethod.POST)
	public ModelAndView editUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return newForm(usuario);
		}
		usuarioService.update(usuario);
		attr.addFlashAttribute("success", "Registro Editado com sucesso.");
		return new ModelAndView("redirect:/usuario/novo_usuario");
	}

	@RequestMapping(value = "/buscar_usuario/{id}", method = RequestMethod.GET)
	public ModelAndView searchUsuario(@PathVariable("id") Long id) {

		Usuario usuario = new Usuario();
		usuario = usuarioService.findUsuarioById(id);
		return newForm(usuario);
	}

	@RequestMapping(value = "/consulta_usuario/{id}", method = RequestMethod.GET)
	public ModelAndView consultaUsuarioByID(@PathVariable("id") Long id) {
		Usuario usuario = new Usuario();
		usuario = usuarioService.findUsuarioById(id);
		ModelAndView mv = new ModelAndView("/usuario/consulta_usuario");
		mv.addObject("usuario", usuario);
		return mv;
	}

	/*
	 * Rotinas para exclusão de registro
	 */
	@RequestMapping(value = "/excluir_usuario/{id}", method = RequestMethod.GET)
	public ModelAndView removeUsuarioById(@PathVariable("id") Long id) {
		Usuario usuario = new Usuario();
		usuario = usuarioService.findUsuarioById(id);
		ModelAndView mv = new ModelAndView("/usuario/excluir_usuario");
		mv.addObject("usuario", usuario);
		return mv;
	}

	@RequestMapping(value = "/excluir_usuario", method = RequestMethod.POST)
	public ModelAndView removeUsuario(Usuario usuario, RedirectAttributes attr) {
		usuarioService.remove(usuario);
		attr.addFlashAttribute("success", "Registro Excluido com sucesso.");
		return new ModelAndView("redirect:/usuario/novo_usuario");
	}
	@RequestMapping(value= {"/salvar_usuario","/editar_usuario","/excluir_usuario"}, method=RequestMethod.POST, params="action=cancelar")
	public String cancelar() {
		return "redirect:/usuario/list_usuario";
	}
	@ModelAttribute("roles")
	public List<Role> getrole() {
		return roleService.findAll();
	}
}
