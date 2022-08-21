package devweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import devweb.service.spec.IProfissionalService;

@Controller
public class HomeController {
	private IProfissionalService service;
    
	// ESSA PÁGINA INICIAL POR ENQUANTO É A PÁGINA DO ADMIN, PRECISA CRIAR A PÁGINA INICIAL AINDA
    @GetMapping("/")
	public String home(ModelMap model) {
    	model.addAttribute("clientes",service.buscarTodos());
		return "home";
	}
}
