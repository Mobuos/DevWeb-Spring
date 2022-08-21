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


import devweb.domain.Agendamento;
import devweb.service.spec.IAgendamentoService;
import devweb.service.spec.IProfissionalService;

@Controller
@RequestMapping("/profissional")
public class ProfissionalController {
	
	@Autowired
	private IAgendamentoService service;

	@Autowired
	private IProfissionalService pService;

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
			System.out.println("Erros no salvamento");
			System.out.println(result.getAllErrors());
			return "profissional/horario/cadastro";
		}

		System.out.println("SALVANDO " + agendamento);
		
		// TODO: Usar CPF do usuário profissional
		agendamento.setProfissional(pService.buscarPorCPF("12345678910"));
		agendamento.setAgendado(false);
		service.salvar(agendamento);
		attr.addFlashAttribute("sucess", "Agendamento inserido com sucesso.");
		return "redirect:/profissional/horario/horarios";
	}
	
	@GetMapping("horario/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, Agendamento agendamento, ModelMap model) {
		Optional<Agendamento> a = service.buscarPorID(id);
		if (!a.isPresent()) {
			System.out.println("======= NÃO ENCONTREI O HORÁRIO DE ID " + id);
			model.addAttribute("fail", "Não foi encontrado o agendamento");
		} else {
			Agendamento ag = a.get();
			System.out.println("======= Horário:" + ag.getData());
			System.out.println("======= Agendado:" + ag.getAgendado());
			System.out.println("======= ID:" + ag.getId());
			System.out.println("======= CPF PRO:" + ag.getProfissional().getCPF());
			model.addAttribute("agendamento", ag);
		}
		
		return "/profissional/horario/cadastro";
	}
	
	@PostMapping("horario/editar")
	public String editar(@Valid Agendamento agendamento, BindingResult result, RedirectAttributes attr) {
		System.out.println("EDITANDO =======================");
		System.out.println("Agendado: " + agendamento.getAgendado());
		System.out.println("Id: " + agendamento.getId());
		System.out.println("Cli CPF: " + agendamento.getCliente());
		System.out.println("Pro CPF: " + agendamento.getProfissional());
		System.out.println("Data: " + agendamento.getData());
		System.out.println("Hora: " + agendamento.getHora());

		if (result.hasErrors()) {
			System.out.println("Erros");
			System.out.println(result.getAllErrors());
			return "/profissional/horario/cadastro";
		}

		System.out.println("Atributos do agendamento: " + agendamento.getId() + agendamento.getData() + agendamento.getHora());
		service.salvar(agendamento);
		attr.addFlashAttribute("sucess", "Horário editado com sucesso.");
		return "redirect:/profissional/horario/horarios";
	}

	@GetMapping("horario/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
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