package devweb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import devweb.dao.IClienteDAO;
import devweb.dao.IProfissionalDAO;
import devweb.domain.Cliente;
import devweb.domain.Profissional;

@SpringBootApplication
public class SAgendamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SAgendamentoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(IClienteDAO clienteDAO, IProfissionalDAO profissionalDAO) {
		return (args) -> {

			Cliente c1 = new Cliente();
			c1.setCPF("43113484871");
			c1.setNome("João Dini");
			c1.setData_nascimento("02/10/2001");
			c1.setEmail("copperbrjdmdini@gmail.com");
			c1.setSenha("6422");
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

			Profissional p1 = new Profissional();
			p1.setCPF("12345678910");
			p1.setNome("prof1");
			p1.setData_nascimento("02/10/2001");
			p1.setEmail("prof1@prof.com");
			p1.setSenha("6422");
			p1.setSexo('M');
			p1.setEspecialidade("especialista");
			p1.setQualificacoes("qualificado");
			p1.setArea_atuacao("area profissional");
			profissionalDAO.save(p1);
		};
		
		
	}
}
