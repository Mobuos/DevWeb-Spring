package devweb.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import devweb.dao.IUsuarioDAO;
import devweb.domain.Agendamento;
import devweb.service.spec.IAgendamentoService;
import devweb.service.spec.IClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private IAgendamentoService service;
	
	@Autowired
	private IClienteService servicec;

	@Autowired
	private IUsuarioDAO uService;

	@RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

	@GetMapping("/")
	public String clienteHome(ModelMap model, Principal principal) {
		System.out.println("EMAIL DO USUÁRIO LOGADO:::" + currentUserName(principal));
		String cpf = uService.findByEmail(currentUserName(principal)).getCPF();
		System.out.println("CPF +============" + cpf);
		model.addAttribute("agendamentos",service.buscarPorCliente(cpf));
		return "cliente/home";
	}

	@GetMapping("horario/cancelar/{ID}")
	public String cancelar(@PathVariable("ID") Long ID, ModelMap model, Principal principal) {
		Optional<Agendamento> a = service.buscarPorID(ID);
		if (!a.isPresent()) {
			model.addAttribute("fail", "Não foi encontrado o agendamento");
		} else {
			Agendamento agendamento = a.get();
			agendamento.setAgendado(false);
			service.salvar(agendamento);
			model.addAttribute("sucess", "Agendamento cancelado com sucesso.");
		}
		return clienteHome(model, principal);
	}

	@GetMapping("agendar/{ID}")
	public String agendar(@PathVariable("ID") Long id, Agendamento agendamento, ModelMap model) {
		model.addAttribute("horarios", service.buscarDisponivelPorProfissional(String.valueOf(id)));
		return "cliente/agendar";
	}

	@GetMapping("agendar/horario/{ID}")
	public String agendarHorario(@PathVariable("ID") Long id, Agendamento agendamento, ModelMap model, Principal principal) {
		Agendamento ag = service.buscarPorID(id).get();
		ag.setAgendado(true);
		System.out.println("EMAIL DO USUÁRIO LOGADO:::" + currentUserName(principal));
		String cpf = uService.findByEmail(currentUserName(principal)).getCPF();
		System.out.println("CPF +============" + cpf);
		ag.setCliente(servicec.buscarPorCPF(cpf));
		service.salvar(ag);
		return "redirect:/cliente/";
	}
	
}
