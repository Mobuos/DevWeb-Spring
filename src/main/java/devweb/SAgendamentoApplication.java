package devweb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import devweb.dao.IClienteDAO;
// import devweb.domain.Admin;
// import devweb.domain.Profissional;
// import devweb.domain.Agendamento;
import devweb.domain.Cliente;
// import devweb.domain.Usuario;

@SpringBootApplication
public class SAgendamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SAgendamentoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IClienteDAO clienteDAO) {
		return (args) -> {

			// Rode na primeira execução?
			Cliente c1 = new Cliente();
			c1.setCPF("43113484871");
			c1.setNome("João Dini");
			c1.setData_nascimento("02/10/2001");
			c1.setEmail("copperbrjdmdini@gmail.com");
			c1.setSenha("6422");
			c1.setSexo('M');
			clienteDAO.save(c1);
		};
	}
}
