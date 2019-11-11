package com.crm.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crm.config.CrmConfig;
import com.crm.controller.page.PageWrapper;
import com.crm.model.Cliente;
import com.crm.model.ClientePessoaFisica;
import com.crm.model.enumarate.Sexo;
import com.crm.repository.filter.ClientePessoaFisicaFilter;
import com.crm.service.ClientePessoaFisicaService;
import com.crm.service.exceptions.EmailClienteExistente;

@Controller
@RequestMapping(value = "/cliente_pessoa_fisica")
public class ClientePessoaFisicaController {
	@Autowired
	ClientePessoaFisicaService clientePessoaFisicaService;
	
	@RequestMapping(value = "/lista_cliente_pessoa_fisica", method = RequestMethod.GET)
	public ModelAndView listaClientePessoaFisica(ClientePessoaFisicaFilter clientePessoaFisicaFilter,
												 HttpServletRequest httpServletRequest,
												 @RequestParam(value = "size", required = false) Optional<Integer> size,
												 @RequestParam(value = "page", required = false) Optional<Integer> page){
		Pageable pageable = PageRequest.of(page.orElse(CrmConfig.INITIAL_PAGE), 
										   size.orElse(CrmConfig.INITIAL_PAGE_SIZE));
		PageWrapper<ClientePessoaFisica> pagina = new PageWrapper<>(clientePessoaFisicaService.
																    listaClientePessoaFisicaComPaginacao(clientePessoaFisicaFilter, pageable),
																	size.orElse(CrmConfig.INITIAL_PAGE_SIZE),
																	httpServletRequest);
		ModelAndView mv = new ModelAndView("/cliente_pessoa_fisica/listar_cliente_pessoa_fisica");
		mv.addObject("pageSizes",CrmConfig.PAGE_SIZES);
		mv.addObject("size",size.orElse(CrmConfig.INITIAL_PAGE_SIZE));
		mv.addObject("pagina", pagina);
		return mv;
	}

	@RequestMapping(value = "/novo_cliente_pessoa_fisica", method = RequestMethod.GET)
	public ModelAndView newForm(ClientePessoaFisica clientePessoaFisica) {
		ModelAndView mv = new ModelAndView("/cliente_pessoa_fisica/cliente_pessoa_fisica");
		mv.addObject("clientePessoaFisica", clientePessoaFisica);
		return mv;
	}

	@RequestMapping(value = "/buscar_cliente_pessoa_fisica/{id}", method = RequestMethod.GET)
	public ModelAndView searchClientePessoaFisica(@PathVariable("id") Long id) {
		ClientePessoaFisica clientePessoaFisica= new ClientePessoaFisica();
		clientePessoaFisica = clientePessoaFisicaService.findClientePessoaFisicaById(id);
		return newForm(clientePessoaFisica);
	}

	@RequestMapping(value = "/salvar_cliente_pessoa_fisica", method = RequestMethod.POST)
	public ModelAndView saveNewClientePessoaFisica(@Valid ClientePessoaFisica clientePessoaFisica, BindingResult result, RedirectAttributes attr) throws Exception {
		if (result.hasErrors()) {
			return newForm(clientePessoaFisica);
		}
		clientePessoaFisicaService.saveClientePessoaFisica(clientePessoaFisica);
		attr.addFlashAttribute("success", "Registro Cadastrado com sucesso.");
		return new ModelAndView("redirect:/cliente_pessoa_fisica/novo_cliente_pessoa_fisica");
	}

	@RequestMapping(value = "/editar_cliente_pessoa_fisica", method = RequestMethod.POST)
	public ModelAndView editClientePessoaFisica(@Valid ClientePessoaFisica clientePessoaFisica, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return newForm(clientePessoaFisica);
		}
		clientePessoaFisicaService.update(clientePessoaFisica);
		attr.addFlashAttribute("success", "Registro Editado com sucesso.");
		return new ModelAndView("redirect:/cliente_pessoa_fisica/novo_cliente_pessoa_fisica");
	}

	@RequestMapping(value = "/excluir_cliente_pessoa_fisica/{id}", method = RequestMethod.GET)
	public ModelAndView removeClientePessoaFisicaById(@PathVariable("id") Long id) {
		ClientePessoaFisica clientePessoaFisica = new ClientePessoaFisica();
		clientePessoaFisica = clientePessoaFisicaService.findClientePessoaFisicaById(id);
		ModelAndView mv = new ModelAndView("/cliente_pessoa_fisica/excluir_cliente_pessoa_fisica");
		mv.addObject("clientePessoaFisica", clientePessoaFisica);
		return mv;
	}

	@RequestMapping(value = "/excluir_cliente_pessoa_fisica", method = RequestMethod.POST)
	public ModelAndView removeClientePessoaFisica(ClientePessoaFisica clientePessoaFisica, RedirectAttributes attr) {
		clientePessoaFisicaService.remove(clientePessoaFisica);
		attr.addFlashAttribute("success", "Registro Excluido com sucesso.");
		return new ModelAndView("redirect:/cliente_pessoa_fisica/novo_cliente_pessoa_fisica");
	}
	@RequestMapping(value = {"/editar_cliente_pessoa_fisica", 
			"/salvar_cliente_pessoa_fisica",
			"/excluir_cliente_pessoa_fisica"}, params = "action=cancelar", method = RequestMethod.POST)
	public String cancelar() {
		return "redirect:/cliente/list_cliente";
	}
	@ModelAttribute("sexos")
	public Sexo[] getSexo() {
		return Sexo.values();
	}
}
