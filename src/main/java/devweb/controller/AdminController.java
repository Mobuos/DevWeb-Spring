package devweb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import devweb.domain.Cliente;
import devweb.service.spec.IClienteService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IClienteService service;

	@GetMapping("/")
	public String adminHome() {
		return "admin/home";
	}
	
	@GetMapping("/cliente/cadastrar")
	public String cadastrar(Cliente cliente) {
		return "admin/cliente/cadastro";
	}
	
	@GetMapping("cliente/listar")
	public String listar(ModelMap model) {
		model.addAttribute("clientes",service.buscarTodos());
		return "admin/cliente/lista";
	}
	
	@PostMapping("cliente/salvar")
	public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "admin/cliente/cadastro";
		}
		
		service.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente inserida com sucesso.");
		return "redirect:/admin/cliente/listar";
	}
	
	// @GetMapping("cliente/editar/{CPF}")
	// public String preEditar(@PathVariable("CPF") String CPF, ModelMap model) {
	// 	model.addAttribute("cliente", service.buscarPorCPF(CPF));
	// 	return "admin/cliente/cadastro";
	// }
	
	// @PostMapping("cliente/editar")
	// public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		
	// 	// Apenas rejeita se o problema não for com o CPF (CPF campo read-only) 
	// 	if (result.getFieldErrorCount() > 1 || result.getFieldError("CPF") == null) {
	// 		return "admin/cliente/cadastro";
	// 	}

	// 	service.salvar(cliente);
	// 	attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
	// 	return "redirect:/admin/clientes/listar";
	// }

	@GetMapping("/cliente/editar/{cpf}")
	public String preEditar(@PathVariable("cpf") String cpf, ModelMap model) {
		model.addAttribute("cliente", service.buscarPorCPF(cpf));
		return "/admin/cliente/cadastro";
	}

	@PostMapping("/cliente/editar")
	public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "/admin/cliente/cadastro";
		}

		service.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
		return "redirect:/admin/cliente/listar";
	}
	
	@GetMapping("cliente/excluir/{CPF}")
	public String excluir(@PathVariable("CPF") String CPF, ModelMap model) {
		// if (service.clienteTemAgendamentos(CPF)) {
		// 	model.addAttribute("fail", "Cliente não excluída. Possui livro(s) vinculado(s).");
		// } else {
			service.excluir(CPF);
			model.addAttribute("sucess", "Cliente excluída com sucesso.");
		// }
		return listar(model);
	}
}
