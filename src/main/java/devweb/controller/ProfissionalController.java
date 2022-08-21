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

import devweb.domain.Profissional;
import devweb.domain.Agendamento;
import devweb.service.spec.IProfissionalService;
import devweb.service.spec.IAgendamentoService;

@Controller
@RequestMapping("/profissional")
public class ProfissionalController {
	
	@Autowired
	private IAgendamentoService service;
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

	@GetMapping("horario/cadastrar")
	public String cadastrar(Agendamento agendamento) {
		return "profissional/horario/cadastro";
	}
	
	@PostMapping("horario/salvar")
	public String salvar(@Valid Agendamento agendamento, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "profissional/horario/cadastro";
		}
		
		service.salvar(agendamento);
		attr.addFlashAttribute("sucess", "Agendamento inserido com sucesso.");
		return "redirect:/profissional/horario/horarios";
	}
	
	@GetMapping("horario/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		Optional<Agendamento> a = service.buscarPorID(id);
		if (!a.isPresent()) {
			model.addAttribute("fail", "Não foi encontrado o agendamento");
		} else {
			Agendamento agendamento = a.get();
			model.addAttribute("horario", agendamento);
		}
		
		return "/profissional/horario/cadastro";
	}
	
	@PostMapping("horario/editar")
	public String editar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "/profissional/horario/cadastro";
		}

		servicep.salvar(profissional);
		attr.addFlashAttribute("sucess", "Profissional editado com sucesso.");
		return "redirect:/profissional/horario/horarios";
	}

	@GetMapping("horario/excluir/{id}")
	public String excluir1(@PathVariable("id") Long id, ModelMap model) {
		Optional<Agendamento> a = service.buscarPorID(id);
		if (!a.isPresent()) {
			model.addAttribute("fail", "Não foi encontrado o horário");
		} else {
			service.excluir(a.get());
			model.addAttribute("sucess", "Horário excluído com sucesso.");
		}
		return horarios(model);
	}
}
