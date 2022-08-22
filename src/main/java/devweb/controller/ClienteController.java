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
import devweb.domain.Cliente;
import devweb.service.spec.IProfissionalService;
import devweb.service.spec.IAgendamentoService;
import devweb.service.spec.IClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private IAgendamentoService service;
	
	@Autowired
	private IClienteService servicec;

	@GetMapping("/")
	public String clienteHome(ModelMap model) {
		// TODO: Usar CPF do usuário profissional
		model.addAttribute("agendamentos",service.buscarPorCliente("75823751853"));
		return "cliente/home";
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
		return clienteHome(model);
	}

	@GetMapping("agendar/{ID}")
	public String agendar(@PathVariable("ID") Long id, Agendamento agendamento, ModelMap model) {
		// TODO: Usar CPF do usuário profissional
		model.addAttribute("horarios", service.buscarDisponivelPorProfissional(String.valueOf(id)));
		return "cliente/agendar";
	}

	@GetMapping("agendar/horario/{ID}")
	public String agendarHorario(@PathVariable("ID") Long id, Agendamento agendamento, ModelMap model) {
		Agendamento ag = service.buscarPorID(id).get();
		ag.setAgendado(true);
		ag.setCliente(servicec.buscarPorCPF("75823751853"));
		service.salvar(ag);
		return "redirect:/cliente/";
	}
	
}
