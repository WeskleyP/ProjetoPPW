package com.crm.controller;

import java.util.List;
import java.util.Optional;

//import java.util.List;

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
import com.crm.model.Cliente;
import com.crm.model.Telefone;
import com.crm.model.enumarate.TipoContato;
import com.crm.model.enumarate.TipoTelefone;
import com.crm.repository.filter.ClienteFilter;
import com.crm.service.ClienteService;
import com.crm.service.TelefoneService;
import com.crm.service.exceptions.EmailClienteExistente;

@Controller
@RequestMapping(value = "/cliente")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private TelefoneService telefoneService;

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

	@RequestMapping(value = "/salvar_cliente", method = RequestMethod.POST, params = "action=salvar")
	public ModelAndView saveNewCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return newForm(cliente);
		}
		try {
			cliente = clienteService.saveCliente(cliente);
			cliente = clienteService.adicionarContatoCliente(cliente);
		} catch (EmailClienteExistente e) {
			result.rejectValue("email", e.getMessage());
			return newForm(cliente);
		}
		ModelAndView mv = new ModelAndView("/cliente/cliente");
		mv.addObject("cliente",cliente);
		attr.addFlashAttribute("success", "Registro Cadastrado com sucesso.");
		return mv;
	}
	@RequestMapping(value =  {"/salvar_cliente","/editar_cliente"}, method = RequestMethod.POST, params = "addrow")
	public ModelAndView cadastrarNovoTelefoneCliente (Cliente cliente) {
		ModelAndView mv = new ModelAndView("/cliente/cliente");
		cliente = clienteService.adicionarContatoCliente(cliente);
		mv.addObject("cliente", cliente);
		return mv;
	}
	@RequestMapping(value =  {"/salvar_cliente","/editar_cliente"}, method = RequestMethod.POST, params = "removerow")
	public ModelAndView excluirTelefoneContatoCliente(Cliente cliente, HttpServletRequest request) {
		int index = Integer.valueOf(request.getParameter("removerow"));
		cliente = clienteService.removeTelefoneCliente(cliente, index);
		ModelAndView mv = new ModelAndView("/cliente/cliente");
		mv.addObject("cliente", cliente);
		return mv;
	}

	@RequestMapping(value = "/editar_cliente", method = RequestMethod.POST)
	public ModelAndView editCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return newForm(cliente);
		}
		clienteService.update(cliente);
		clienteService.salvarTelefoneCliente(cliente);
		attr.addFlashAttribute("success", "Registro Editado com sucesso.");
		return new ModelAndView("redirect:/cliente/novo_cliente");
	}
	@RequestMapping(value = "/buscar_cliente/{id}", method = RequestMethod.GET)
	public ModelAndView searchCliente(@PathVariable("id") Long id) {
		Cliente cliente = new Cliente();
		cliente = clienteService.findClienteByIdAndTelefone(id);
		/*
		 * List<Telefone> listaTelefone =
		 * telefoneService.findTelefoneClienteById(cliente.getId()); for(Telefone
		 * telefone : listaTelefone) { cliente.getListaTelefones().add(telefone); }
		 */
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
	@RequestMapping(value = {"/salvar_cliente","/editar_cliente"}, method = RequestMethod.POST, params = "fone")
	public ModelAndView salvarTelefoneCliente(@Valid Cliente cliente) {
		clienteService.update(cliente);
		clienteService.salvarTelefoneCliente(cliente);
		return new ModelAndView("redirect:/cliente/list_cliente");
	}
	@ModelAttribute("tipoTelefones")
	public TipoTelefone[] getTipoTelefone() {
		return TipoTelefone.values();
	}
	@ModelAttribute("tipoContatos")
	public TipoContato[] getTipoContato() {
		return TipoContato.values();
	}

}