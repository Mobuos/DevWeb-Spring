package devweb.controller;

import java.util.Optional;

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
import static org.springframework.data.util.Optionals.ifPresentOrElse;

import devweb.domain.Cliente;
import devweb.domain.Profissional;
import devweb.domain.Agendamento;
import devweb.service.spec.IClienteService;
import devweb.service.spec.IProfissionalService;
import devweb.service.spec.IAgendamentoService;

@Controller
@RequestMapping("/profissional")
public class ProfissionalController {
	
	@Autowired
	private IAgendamentoService service;
	@Autowired
	private IClienteService servicec;
	@Autowired
	private IProfissionalService servicep;

	@GetMapping("/")
	public String profissionalHome(ModelMap model) {
		// TODO: Usar CPF do usuário profissional
		model.addAttribute("agendamentos",service.buscarPorProfissional("12345678910"));
		return "profissional/home";
	}

	@GetMapping("horario/cancelar/{ID}")
	public String cancelar(@PathVariable("ID") Long ID, ModelMap model) {
		Optional<Agendamento> a = service.buscarPorID(ID);
		if (!a.isPresent()) {
			model.addAttribute("fail", "Não foi encontrado o agendamento");
		} else {
			Agendamento agendamento = a.get();
			agendamento.setAgendado(false);
			service.salvar(agendamento);
			model.addAttribute("sucess", "Agendamento cancelado com sucesso.");
		}
		return profissionalHome(model);
	}
	
	// Listar e cadastrar estão aqui
	@GetMapping("horario/horarios")
	public String horarios(ModelMap model) {
		// TODO: Usar CPF do usuário profissional
		model.addAttribute("horarios", service.buscarDisponivelPorProfissional("12345678910"));
		return "profissional/horario/horarios";
	}

	// @GetMapping("/profissional/cadastrar")
	// public String cadastrar(Profissional profissional) {
	// 	return "admin/profissional/cadastro";
	// }
	
	// @PostMapping("profissional/salvar")
	// public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {
		
	// 	if (result.hasErrors()) {
	// 		return "admin/profissional/cadastro";
	// 	}
		
	// 	servicep.salvar(profissional);
	// 	attr.addFlashAttribute("sucess", "Profissional inserido com sucesso.");
	// 	return "redirect:/admin/profissional/listar";
	// }
	
	// @GetMapping("/profissional/editar/{cpf}")
	// public String preEditar1(@PathVariable("cpf") String cpf, ModelMap model) {
	// 	model.addAttribute("profissional", servicep.buscarPorCPF(cpf));
	// 	return "/admin/profissional/cadastro";
	// }
	
	// @PostMapping("/profissional/editar")
	// public String editar1(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {

	// 	if (result.hasErrors()) {
	// 		return "/admin/profissional/cadastro";
	// 	}

	// 	servicep.salvar(profissional);
	// 	attr.addFlashAttribute("sucess", "Profissional editado com sucesso.");
	// 	return "redirect:/admin/profissional/listar";
	// }
}
