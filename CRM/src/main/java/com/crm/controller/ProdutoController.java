package com.crm.controller;

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
import com.crm.model.Produto;
import com.crm.model.enumarate.Sexo;
import com.crm.repository.filter.CategoriaFilter;
import com.crm.repository.filter.ProdutoFilter;
import com.crm.service.ProdutoService;
import com.crm.service.exceptions.EmailClienteExistente;

@Controller
@RequestMapping(value = "/produto")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(value = "/list_produto", method = RequestMethod.GET)
	public ModelAndView listProduto(ProdutoFilter produtoFilter, HttpServletRequest httpServletRequest,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "page", required = false) Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(CrmConfig.INITIAL_PAGE),
				size.orElse(CrmConfig.INITIAL_PAGE_SIZE));
		PageWrapper<Produto> pagina = new PageWrapper<>(
				produtoService.listaProdutoComPaginacao(produtoFilter, pageable),
				size.orElse(CrmConfig.INITIAL_PAGE_SIZE), httpServletRequest);
		ModelAndView mv = new ModelAndView("/produto/listar_produto");
		mv.addObject("pageSizes", CrmConfig.PAGE_SIZES);
		mv.addObject("size", size.orElse(CrmConfig.INITIAL_PAGE_SIZE));
		mv.addObject("pagina", pagina);
		return mv;
	}

	/*
	 * Rotinas para inclusão e alteração de registro
	 */
	@RequestMapping(value = "/novo_produto", method = RequestMethod.GET)
	public ModelAndView newForm(Produto produto) {
		ModelAndView mv = new ModelAndView("/produto/produto");
		mv.addObject("produto", produto);
		return mv;
	}

	@RequestMapping(value = "/salvar_produto", method = RequestMethod.POST)
	public ModelAndView saveNewProduto(@Valid Produto produto, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return newForm(produto);
		}
		try {
			produtoService.saveProduto(produto);
		} catch (EmailClienteExistente e) {
			result.rejectValue("email", e.getMessage());
			return newForm(produto);
		}
		attr.addFlashAttribute("success", "Registro Cadastrado com sucesso.");
		return new ModelAndView("redirect:/produto/novo_produto");
	}

	@RequestMapping(value = "/editar_produto", method = RequestMethod.POST)
	public ModelAndView editProduto(@Valid Produto produto, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return newForm(produto);
		}
		produtoService.update(produto);
		attr.addFlashAttribute("success", "Registro Editado com sucesso.");
		return new ModelAndView("redirect:/produto/novo_produto");
	}

	@RequestMapping(value = "/buscar_produto/{id}", method = RequestMethod.GET)
	public ModelAndView searchProduto(@PathVariable("id") Long id) {

		Produto produto = new Produto();
		produto = produtoService.findProdutoById(id);
		return newForm(produto);
	}

	@RequestMapping(value = "/consulta_produto/{id}", method = RequestMethod.GET)
	public ModelAndView consultaProdutoByID(@PathVariable("id") Long id) {
		Produto produto = new Produto();
		produto = produtoService.findProdutoById(id);
		ModelAndView mv = new ModelAndView("/produto/consulta_produto");
		mv.addObject("produto", produto);
		return mv;
	}

	/*
	 * Rotinas para exclusão de registro
	 */
	@RequestMapping(value = "/excluir_produto/{id}", method = RequestMethod.GET)
	public ModelAndView removeProdutoById(@PathVariable("id") Long id) {
		Produto produto = new Produto();
		produto = produtoService.findProdutoById(id);
		ModelAndView mv = new ModelAndView("/produto/excluir_produto");
		mv.addObject("produto", produto);
		return mv;
	}

	@RequestMapping(value = "/excluir_produto", method = RequestMethod.POST)
	public ModelAndView removeProduto(Produto produto, RedirectAttributes attr) {
		produtoService.remove(produto);
		attr.addFlashAttribute("success", "Registro Excluido com sucesso.");
		return new ModelAndView("redirect:/produto/novo_produto");
	}
	@RequestMapping(value= {"/salvar_produto","/editar_produto","/excluir_produto"}, method=RequestMethod.POST, params="action=cancelar")
	public String cancelar() {
		return "redirect:/produto/list_produto";
	}
}
