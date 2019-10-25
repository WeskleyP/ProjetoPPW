package com.crm.controller;

import java.util.Optional;

//import java.util.List;

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
import com.crm.model.Cliente;
import com.crm.repository.filter.ClienteFilter;
import com.crm.service.ClienteService;
import com.crm.service.exceptions.EmailClienteExistente;

@Controller
@RequestMapping(value = "/cliente")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/list_cliente", method = RequestMethod.GET)
	public ModelAndView listCliente(ClienteFilter clienteFilter, HttpServletRequest httpServletRequest,
			@RequestParam(value = "size", required = false) Optional<Integer> size,
			@RequestParam(value = "page", required = false) Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(CrmConfig.INITIAL_PAGE),
				size.orElse(CrmConfig.INITIAL_PAGE_SIZE));
		PageWrapper<Cliente> pagina = new PageWrapper<>(
				clienteService.listaClienteComPaginacao(clienteFilter, pageable),
				size.orElse(CrmConfig.INITIAL_PAGE_SIZE), httpServletRequest);
		ModelAndView mv = new ModelAndView("/cliente/listar_cliente");
		mv.addObject("pageSizes", CrmConfig.PAGE_SIZES);
		mv.addObject("size", size.orElse(CrmConfig.INITIAL_PAGE_SIZE));
		mv.addObject("pagina", pagina);
		return mv;
	}

	/*
	 * Rotinas para inclusão e alteração de registro
	 */
	@RequestMapping(value = "/novo_cliente", method = RequestMethod.GET)
	public ModelAndView newForm(Cliente cliente) {
		ModelAndView mv = new ModelAndView("/cliente/cliente");
		mv.addObject("cliente", cliente);
		return mv;
	}

	@RequestMapping(value = "/salvar_cliente", method = RequestMethod.POST)
	public ModelAndView saveNewCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return newForm(cliente);
		}
		try {
			clienteService.saveCliente(cliente);
		} catch (EmailClienteExistente e) {
			result.rejectValue("email", e.getMessage());
			return newForm(cliente);
		}
		attr.addFlashAttribute("success", "Registro Cadastrado com sucesso.");
		return new ModelAndView("redirect:/cliente/novo_cliente");
	}

	@RequestMapping(value = "/editar_cliente", method = RequestMethod.POST)
	public ModelAndView editCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return newForm(cliente);
		}
		clienteService.update(cliente);
		attr.addFlashAttribute("success", "Registro Editado com sucesso.");
		return new ModelAndView("redirect:/cliente/novo_cliente");
	}

	@RequestMapping(value = "/buscar_cliente/{id}", method = RequestMethod.GET)
	public ModelAndView searchCliente(@PathVariable("id") Long id) {

		Cliente cliente = new Cliente();
		cliente = clienteService.findClienteById(id);
		return newForm(cliente);
	}

	@RequestMapping(value = "/consulta_cliente/{id}", method = RequestMethod.GET)
	public ModelAndView consultaClienteByID(@PathVariable("id") Long id) {
		Cliente cliente = new Cliente();
		cliente = clienteService.findClienteById(id);
		ModelAndView mv = new ModelAndView("/cliente/consulta_cliente");
		mv.addObject("cliente", cliente);
		return mv;
	}

	/*
	 * Rotinas para exclusão de registro
	 */
	@RequestMapping(value = "/excluir_cliente/{id}", method = RequestMethod.GET)
	public ModelAndView removeClienteById(@PathVariable("id") Long id) {
		Cliente cliente = new Cliente();
		cliente = clienteService.findClienteById(id);
		ModelAndView mv = new ModelAndView("/cliente/excluir_cliente");
		mv.addObject("cliente", cliente);
		return mv;
	}

	@RequestMapping(value = "/excluir_cliente", method = RequestMethod.POST)
	public ModelAndView removeCliente(Cliente cliente, RedirectAttributes attr) {
		clienteService.remove(cliente);
		attr.addFlashAttribute("success", "Registro Excluido com sucesso.");
		return new ModelAndView("redirect:/cliente/novo_cliente");
	}
	@RequestMapping(value= {"/salvar_cliente","/editar_cliente","/excluir_cliente"}, method=RequestMethod.POST, params="action=cancelar")
	public String cancelar() {
		return "redirect:/cliente/list_cliente";
	}

}