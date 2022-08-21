package devweb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import devweb.domain.Agendamento;
import devweb.service.spec.IAgendamentoService;
import devweb.service.spec.IClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private IAgendamentoService service;

	@GetMapping("/")
	public String clienteHome(ModelMap model) {
		// TODO: Usar CPF do usuário cliente
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
	
}
