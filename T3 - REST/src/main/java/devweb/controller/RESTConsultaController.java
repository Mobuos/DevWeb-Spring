package devweb.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;  

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import devweb.domain.Cliente;
import devweb.domain.Profissional;
import devweb.domain.Agendamento;
import devweb.service.spec.IAgendamentoService;

@CrossOrigin
@RestController
public class RESTConsultaController {
	
	@Autowired
	private IAgendamentoService aService;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
    }
	
	private void parse(Agendamento agendamento, JSONObject json){

		agendamento.setAgendado( ((String) json.get("agendado")) == "true" ? true : false);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    }
	
    // Retorna a lista de agendamentos
    @GetMapping(path = "/api/agendamentos")
    public ResponseEntity<List<Agendamento>> lista() {
        List<Agendamento> lista = aService.buscaTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }
	
}






