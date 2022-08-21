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

			Cliente c1 = new Cliente();
			c1.setCPF("12345464366");
			c1.setNome("Augusto Matias");
			c1.setData_nascimento("02/10/2001");
			c1.setEmail("augusto@gmail.com");
			c1.setSenha("39183");
			c1.setSexo('M');
			clienteDAO.save(c1);

			Cliente c2 = new Cliente();
			c2.setCPF("75823751853");
			c2.setNome("Paulo Sebastião");
			c2.setData_nascimento("10/10/1998");
			c2.setEmail("p.sebastiao@gmail.com");
			c2.setSenha("928ab");
			c2.setSexo('M');
			clienteDAO.save(c2);
		};
	}
}
