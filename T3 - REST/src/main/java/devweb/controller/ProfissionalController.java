package devweb.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import devweb.dao.IUsuarioDAO;
import devweb.domain.Agendamento;
import devweb.service.spec.IAgendamentoService;
import devweb.service.spec.IProfissionalService;

@Controller
@RequestMapping("/profissional")
public class ProfissionalController {

	@RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
	
	@Autowired
	private IAgendamentoService service;

	@Autowired
	private IProfissionalService pService;

	@Autowired
	private IUsuarioDAO uService;

	@GetMapping("/")
	public String profissionalHome(ModelMap model, Principal principal) {
		System.out.println("EMAIL DO USUÁRIO LOGADO:::" + currentUserName(principal));
		String cpf = uService.findByEmail(currentUserName(principal)).getCPF();
		System.out.println("CPF +============" + cpf);
		model.addAttribute("agendamentos",service.buscarPorProfissional(cpf));
		return "profissional/home";
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
		return profissionalHome(model, principal);
	}
	
	// Listar e cadastrar estão aqui
	@GetMapping("horario/horarios")
	public String horarios(ModelMap model, Principal principal) {
		System.out.println("EMAIL DO USUÁRIO LOGADO:::" + currentUserName(principal));
		String cpf = uService.findByEmail(currentUserName(principal)).getCPF();
		System.out.println("CPF +============" + cpf);
		model.addAttribute("horarios", service.buscarDisponivelPorProfissional(cpf));
		return "profissional/horario/horarios";
	}

	@GetMapping("horario/cadastrar")
	public String cadastrar(Agendamento agendamento) {
		return "profissional/horario/cadastro";
	}
	
	@PostMapping("horario/salvar")
	public String salvar(@Valid Agendamento agendamento, BindingResult result, RedirectAttributes attr, Principal principal) {
		System.out.println("SALVANDO " + agendamento);
		if (result.hasErrors()) {
			return "profissional/horario/cadastro";
		}
		
		System.out.println("EMAIL DO USUÁRIO LOGADO:::" + currentUserName(principal));
		String cpf = uService.findByEmail(currentUserName(principal)).getCPF();
		System.out.println("CPF +============" + cpf);
		agendamento.setProfissional(pService.buscarPorCPF(cpf));
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
	public String editar(@Valid Agendamento agendamento, BindingResult result, RedirectAttributes attr, ModelMap model) {
		System.out.println("EDITANDO =======================");
		if (result.hasErrors()) {
			return "/profissional/horario/cadastro";
		}

		service.salvar(agendamento);
		attr.addFlashAttribute("sucess", "Horário editado com sucesso.");
		return "redirect:/profissional/horario/horarios";
	}

	@GetMapping("horario/excluir/{id}")
	public String excluir1(@PathVariable("id") Long id, ModelMap model, Principal principal) {
		Optional<Agendamento> a = service.buscarPorID(id);
		if (!a.isPresent()) {
			model.addAttribute("fail", "Não foi encontrado o horário");
		} else {
			service.excluir(a.get());
			model.addAttribute("sucess", "Horário excluído com sucesso.");
		}
		return horarios(model, principal);
	}
}