// package devweb.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.ModelMap;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// import devweb.service.spec.IProfissionalService;

// @Controller
// @RequestMapping("/")
// public class HomeController {
	
// 	@Autowired
// 	private IProfissionalService service;
    
// 	// ESSA PÁGINA INICIAL POR ENQUANTO É A PÁGINA DO ADMIN, PRECISA CRIAR A PÁGINA INICIAL AINDA
//     @GetMapping("/")
// 	public String home(ModelMap model) {
//     	model.addAttribute("profissionais", service.buscarTodos());
// 		return "home";
// 	}
// }
