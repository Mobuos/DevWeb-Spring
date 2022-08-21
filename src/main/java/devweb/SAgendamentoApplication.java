package devweb;

import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import devweb.dao.IAgendamentoDAO;
import devweb.dao.IClienteDAO;
import devweb.dao.IProfissionalDAO;
import devweb.domain.Agendamento;
import devweb.domain.Cliente;
import devweb.domain.Profissional;

@SpringBootApplication
public class SAgendamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SAgendamentoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(IClienteDAO clienteDAO, IProfissionalDAO profissionalDAO, IAgendamentoDAO agendamentoDAO) {
		return (args) -> {

			Cliente c1 = new Cliente();
			c1.setCPF("13443189861");
			c1.setNome("Miranda Miranda");
			c1.setData_nascimento("02/10/2001");
			c1.setEmail("copperbrjdmdini@gmail.com");
			c1.setSenha("9544");
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
			p1.setSenha("7653");
			p1.setSexo('M');
			p1.setEspecialidade("especialista");
			p1.setQualificacoes("qualificado");
			p1.setArea_atuacao("area profissional");
			profissionalDAO.save(p1);

			Profissional p2 = new Profissional();
			p2.setCPF("53345677778");
			p2.setNome("prof2");
			p2.setData_nascimento("12/12/1986");
			p2.setEmail("prof2@prof.com");
			p2.setSenha("5464");
			p2.setSexo('F');
			p2.setEspecialidade("especialista");
			p2.setQualificacoes("qualificado");
			p2.setArea_atuacao("area profissional");
			profissionalDAO.save(p2);

			SimpleDateFormat dF = new SimpleDateFormat("dd-MM-yyyy"); // Date Formatter
			SimpleDateFormat tF = new SimpleDateFormat("HH:mm:ss"); // Time Formatter

			Agendamento a1 = new Agendamento();
			a1.setCliente(c1);
			a1.setProfissional(p1);
			a1.setData(dF.parse("30-08-2022"));
			a1.setHora(tF.parse("13:30:00"));
			agendamentoDAO.save(a1);

			Agendamento a2 = new Agendamento();
			a2.setCliente(c2);
			a2.setProfissional(p1);
			a2.setData(dF.parse("12-09-2022"));
			a2.setHora(tF.parse("10:00:00"));
			agendamentoDAO.save(a2);

			Agendamento a3 = new Agendamento();
			a3.setCliente(c1);
			a3.setProfissional(p2);
			a3.setData(dF.parse("15-09-2022"));
			a3.setHora(tF.parse("11:00:00"));
			agendamentoDAO.save(a3);
		};
		
		
	}
}
