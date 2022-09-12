package devweb.controller;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import devweb.domain.Cliente;
import devweb.domain.Profissional;
import devweb.service.spec.IClienteService;
import devweb.service.spec.IProfissionalService;

@CrossOrigin
@RestController
public class RESTProfissionalController {
    
    @Autowired
    private IProfissionalService pService;

    private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
    }

	private void parse(Profissional profissional, JSONObject json) {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				profissional.setId(((Integer) id).longValue());
			} else {
				profissional.setId((Long) id);
			}
		}

		
		profissional.setNome((String) json.get("nome"));
		profissional.setCPF((String) json.get("cpf"));
		profissional.setData_nascimento((String) json.get("data_de_nascimento"));
		profissional.setEmail((String) json.get("email"));
		profissional.setSenha((String) json.get("senha"));
		profissional.setSexo(((String) json.get("sexo")).charAt(0));
		profissional.setTelefone((String) json.get("telefone"));
		profissional.setArea_atuacao((String) json.get("area_atuacao"));
		profissional.setEspecialidade((String) json.get("especialidade"));
		profissional.setQualificacoes((String) json.get("qualificacoes"));
    }

    // Cria um profissional
    @PostMapping(path = "/api/profissionais")
	@ResponseBody
	public ResponseEntity<Profissional> cria(@RequestBody JSONObject json) {    

		try {
			
			if (isJSONValid(json.toString())) {
				
				Profissional profissional = new Profissional();
				parse(profissional, json);
				pService.salvar(profissional);
				
				return ResponseEntity.ok(profissional);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
    }

    // Retorna a lista de profissionais
    @GetMapping(path = "/api/profissionais")
    public ResponseEntity<List<Profissional>> lista(@RequestParam(required = false) String especialidade) {
    	List<Profissional> lista = null;
    	if(especialidade != null) {
    		lista = pService.findByEspecialidade(especialidade);
    	}else {
    		
    		lista = pService.buscarTodos();
    		if (lista.isEmpty()) {
    			return ResponseEntity.notFound().build();
    		}	
    	}
        return ResponseEntity.ok(lista);
    }

	@GetMapping(path = "/api/profissionais/especialidades/{name}")
    public ResponseEntity<List<Profissional>> listaEsp(@PathVariable("name") String name) {
    	List<Profissional> lista = null;
		lista = pService.findByEspecialidade(name);
		if (lista.isEmpty()){
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(lista);
		}
    }
    
    
    // Retorna o cliente de id = {id}
    @GetMapping(path = "/api/profissionais/{id}")
    public ResponseEntity<Profissional> lista(@PathVariable("id") Long id, @RequestParam(required = false) String especialidade) {
    	
        Profissional profissional = pService.buscarPorId(id);
        if (profissional == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profissional);
    }

    // Atualiza o profissional de id = {id}
    @PutMapping(path = "/api/profissionais/{id}")
	public ResponseEntity<Profissional> atualiza(@PathVariable("id") Long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Profissional profissional = pService.buscarPorId(id);
				if (profissional == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(profissional, json);
					pService.salvar(profissional);
					return ResponseEntity.ok(profissional);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
    }

    // Deleta um cliente
    @DeleteMapping(path = "/api/profissionais/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") Long id) {
        Profissional profissional = pService.buscarPorId(id);
        if (profissional == null) {
            return ResponseEntity.notFound().build();
        } else {
            pService.excluir(profissional.getCPF());
            return ResponseEntity.noContent().build();
        }
    }
}

