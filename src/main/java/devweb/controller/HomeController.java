package devweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
	// ESSA PÁGINA INICIAL POR ENQUANTO É A PÁGINA DO ADMIN, PRECISA CRIAR A PÁGINA INICIAL AINDA
    @GetMapping("/")
	public String home() {
		return "home";
	}
}
