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
import com.crm.model.Categoria;
import com.crm.repository.filter.CategoriaFilter;
import com.crm.repository.filter.ClienteFilter;
import com.crm.service.CategoriaService;
import com.crm.service.ClienteService;
import com.crm.service.exceptions.EmailClienteExistente;

@Controller
@RequestMapping(value = "/categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(value = "/list_categoria", method = RequestMethod.GET)
	public ModelAndView listCategoria(CategoriaFilter categoriaFilter, HttpServletRequest httpServletRequest,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "page", required = false) Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(CrmConfig.INITIAL_PAGE),
				size.orElse(CrmConfig.INITIAL_PAGE_SIZE));
		PageWrapper<Categoria> pagina = new PageWrapper<>(
				categoriaService.listaCategoriaComPaginacao(categoriaFilter, pageable),
				size.orElse(CrmConfig.INITIAL_PAGE_SIZE), httpServletRequest);
		ModelAndView mv = new ModelAndView("/categoria/listar_categoria");
		mv.addObject("pageSizes", CrmConfig.PAGE_SIZES);
		mv.addObject("size", size.orElse(CrmConfig.INITIAL_PAGE_SIZE));
		mv.addObject("pagina", pagina);
		return mv;
	}

	/*
	 * Rotinas para inclusão e alteração de registro
	 */
	@RequestMapping(value = "/nova_categoria", method = RequestMethod.GET)
	public ModelAndView newForm(Categoria categoria) {
		ModelAndView mv = new ModelAndView("/categoria/categoria");
		mv.addObject("categoria", categoria);
		return mv;
	}

	@RequestMapping(value = "/salvar_categoria", method = RequestMethod.POST)
	public ModelAndView saveNewCategoria(@Valid Categoria categoria, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return newForm(categoria);
		}
		try {
			categoriaService.saveCategoria(categoria);
		} catch (EmailClienteExistente e) {
			result.rejectValue("email", e.getMessage());
			return newForm(categoria);
		}
		attr.addFlashAttribute("success", "Registro Cadastrado com sucesso.");
		return new ModelAndView("redirect:/categoria/nova_categoria");
	}

	@RequestMapping(value = "/editar_categoria", method = RequestMethod.POST)
	public ModelAndView editCategoria(@Valid Categoria categoria, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return newForm(categoria);
		}
		categoriaService.update(categoria);
		attr.addFlashAttribute("success", "Registro Editado com sucesso.");
		return new ModelAndView("redirect:/categoria/nova_categoria");
	}

	@RequestMapping(value = "/buscar_categoria/{id}", method = RequestMethod.GET)
	public ModelAndView searchCategoria(@PathVariable("id") Long id) {

		Categoria categoria = new Categoria();
		categoria = categoriaService.findCategoriaById(id);
		return newForm(categoria);
	}

	@RequestMapping(value = "/consulta_categoria/{id}", method = RequestMethod.GET)
	public ModelAndView consultaCategoriaByID(@PathVariable("id") Long id) {
		Categoria categoria = new Categoria();
		categoria = categoriaService.findCategoriaById(id);
		ModelAndView mv = new ModelAndView("/categoria/consulta_categoria");
		mv.addObject("categoria", categoria);
		return mv;
	}

	/*
	 * Rotinas para exclusão de registro
	 */
	@RequestMapping(value = "/excluir_categoria/{id}", method = RequestMethod.GET)
	public ModelAndView removeCategoriaById(@PathVariable("id") Long id) {
		Categoria categoria = new Categoria();
		categoria = categoriaService.findCategoriaById(id);
		ModelAndView mv = new ModelAndView("/categoria/excluir_categoria");
		mv.addObject("categoria", categoria);
		return mv;
	}

	@RequestMapping(value = "/excluir_categoria", method = RequestMethod.POST)
	public ModelAndView removeCliente(Categoria categoria, RedirectAttributes attr) throws Exception {
		try {
			categoriaService.remove(categoria);
			attr.addFlashAttribute("success", "Registro Excluido com sucesso.");
		} catch (Exception e) {
			attr.addFlashAttribute("fail","Categoria relacionada a pelo menos 1 produto");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/categoria/nova_categoria");
		
	}
	@RequestMapping(value= {"/salvar_categoria","/editar_categoria","/excluir_categoria"}, method=RequestMethod.POST, params="action=cancelar")
	public String cancelar() {
		return "redirect:/categoria/list_categoria";
	}
}
