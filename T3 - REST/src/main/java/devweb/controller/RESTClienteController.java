package devweb.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import devweb.domain.Cliente;
import devweb.service.spec.IClienteService;

@CrossOrigin
@RestController
public class RESTClienteController {
    
    @Autowired
    private IClienteService cService;

    private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
    }

	private void parse(Cliente cliente, JSONObject json) {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				cliente.setId(((Integer) id).longValue());
			} else {
				cliente.setId((Long) id);
			}
 	}

		cliente.setNome((String) json.get("nome"));
		cliente.setCPF((String) json.get("cpf"));
        cliente.setData_nascimento((String) json.get("data_de_nascimento"));
        cliente.setEmail((String) json.get("email"));
        cliente.setSenha((String) json.get("senha"));
        cliente.setSexo((Character) json.get("sexo"));
        cliente.setTelefone((String) json.get("telefone"));
    }

    // Cria um cliente
    // @GetMapping(path = "/clientes")
    // public ResponseEntity<List<Cliente>> lista() {
    //     List<Cliente> lista = cService.buscarTodos();
    //     if (lista.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     return ResponseEntity.ok(lista);
    // }

    // Retorna a lista de clientes
    @GetMapping(path = "/api/clientes")
    public ResponseEntity<List<Cliente>> lista() {
        List<Cliente> lista = cService.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    // Retorna o cliente de id = {id}
    @GetMapping(path = "/api/clientes/{id}")
    public ResponseEntity<Cliente> lista(@PathVariable("id") long id) {
        Cliente cliente = cService.buscarPorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    // Atualiza o cliente de id = {id}
    // @GetMapping(path = "/clientes")
    // public ResponseEntity<List<Cliente>> lista() {
    //     List<Cliente> lista = cService.buscarTodos();
    //     if (lista.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     return ResponseEntity.ok(lista);
    // }
}
