package devweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import devweb.dao.IUsuarioDAO;
import devweb.domain.Usuario;

@Controller
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private IUsuarioDAO dao;
    
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentEmail(Authentication auth) {
        return auth.getName();
    }

    @GetMapping("/")
    public String conta(Authentication auth){
        String email = currentEmail(auth);
        String s = dao.findByEmail(email).getRole();
        
        if (s.equals("ROLE_CLIENTE")){
            return "cliente/home";
        } else if (s.equals("ROLE_PROFISSIONAL")) {
            return "profissional/home";
        } else if (s.equals("ROLE_ADMIN")){
            return "admin/home";
        } else {
            return "home";
        }
    }
}
