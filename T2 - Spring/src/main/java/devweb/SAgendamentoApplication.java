package devweb;

import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import devweb.dao.IAdminDAO;
import devweb.dao.IAgendamentoDAO;
import devweb.dao.IClienteDAO;
import devweb.dao.IProfissionalDAO;
import devweb.domain.Admin;
import devweb.domain.Agendamento;
import devweb.domain.Cliente;
import devweb.domain.Profissional;

@SpringBootApplication
public class SAgendamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SAgendamentoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(IClienteDAO clienteDAO, IProfissionalDAO profissionalDAO, IAgendamentoDAO agendamentoDAO, IAdminDAO adminDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {

			Cliente c1 = new Cliente();
			c1.setCPF("13443189861");
			c1.setNome("Miranda Miranda");
			c1.setData_nascimento("02/10/2001");
			c1.setEmail("miranda@gmail.com");
			c1.setSenha(encoder.encode("cliente"));
			c1.setSexo('M');
			clienteDAO.save(c1);

			Cliente c2 = new Cliente();
			c2.setCPF("75823751853");
			c2.setNome("Paulo Sebastião");
			c2.setData_nascimento("10/10/1998");
			c2.setEmail("p.sebastiao@gmail.com");
			c2.setSenha(encoder.encode("cliente"));
			c2.setSexo('M');
			clienteDAO.save(c2);

			Profissional p1 = new Profissional();
			p1.setCPF("12345678910");
			p1.setNome("Alan Robertos");
			p1.setData_nascimento("13/12/2000");
			p1.setEmail("alan@prof.com");
			p1.setSenha(encoder.encode("pro"));
			p1.setSexo('M');
			p1.setEspecialidade("Direito Digital");
			p1.setQualificacoes("qualificado");
			p1.setArea_atuacao("Advocacia");
			profissionalDAO.save(p1);

			Profissional p2 = new Profissional();
			p2.setCPF("53345677778");
			p2.setNome("Ana Paula");
			p2.setData_nascimento("12/12/1986");
			p2.setEmail("a.paula@prof.com");
			p2.setSenha(encoder.encode("pro"));
			p2.setSexo('F');
			p2.setEspecialidade("Mecânica");
			p2.setQualificacoes("qualificado");
			p2.setArea_atuacao("Engenharia");
			p2.setRole("PROFISSIONAL");
			profissionalDAO.save(p2);
			
			Profissional p3 = new Profissional();
			p3.setCPF("1313131313");
			p3.setNome("Martha Magalhães");
			p3.setData_nascimento("02/10/2001");
			p3.setEmail("mmagalhaes@prof.com");
			p3.setSenha(encoder.encode("pro"));
			p3.setSexo('F');
			p3.setEspecialidade("Ortopedia");
			p3.setQualificacoes("code.pdf");
			p3.setArea_atuacao("Medicina");
			profissionalDAO.save(p3);
			
			Profissional p4 = new Profissional();
			p4.setCPF("1313131314");
			p4.setNome("Glauber");
			p4.setData_nascimento("02/10/2001");
			p4.setEmail("glauber.gg@prof.com");
			p4.setSenha(encoder.encode("pro"));
			p4.setSexo('M');
			p4.setEspecialidade("Direito Tributario");
			p4.setQualificacoes("code.pdf");
			p4.setArea_atuacao("Advocacia");
			profissionalDAO.save(p4);

			Profissional p5 = new Profissional();
			p5.setCPF("90383718284");
			p5.setNome("Olivier Florence");
			p5.setData_nascimento("13/12/2000");
			p5.setEmail("oli@prof.com");
			p5.setSenha(encoder.encode("pro"));
			p5.setSexo('M');
			p5.setEspecialidade("Fantasmas");
			p5.setQualificacoes("n/a");
			p5.setArea_atuacao("Medicina");
			profissionalDAO.save(p5);

			Admin adm = new Admin();
			adm.setCPF("adm");
			adm.setNome("Dev");
			adm.setEmail("adm@adm.com");
			adm.setSenha(encoder.encode("adm"));
			adminDAO.save(adm);

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

			Agendamento a9 = new Agendamento();
			a9.setCliente(c2);
			a9.setProfissional(p2);
			a9.setData(dF.parse("23-09-2022"));
			a9.setHora(tF.parse("12:00:00"));
			agendamentoDAO.save(a9);

			// Horários Disponíveis
			Agendamento a4 = new Agendamento();
			a4.setAgendado(false);
			a4.setProfissional(p1);
			a4.setData(dF.parse("16-09-2022"));
			a4.setHora(tF.parse("11:00:00"));
			agendamentoDAO.save(a4);

			Agendamento a5 = new Agendamento();
			a5.setAgendado(false);
			a5.setProfissional(p1);
			a5.setData(dF.parse("18-09-2022"));
			a5.setHora(tF.parse("12:00:00"));
			agendamentoDAO.save(a5);

			Agendamento a8 = new Agendamento();
			a8.setAgendado(false);
			a8.setProfissional(p1);
			a8.setData(dF.parse("20-09-2022"));
			a8.setHora(tF.parse("12:00:00"));
			agendamentoDAO.save(a8);

			Agendamento a6 = new Agendamento();
			a6.setAgendado(false);
			a6.setProfissional(p2);
			a6.setData(dF.parse("17-09-2022"));
			a6.setHora(tF.parse("11:00:00"));
			agendamentoDAO.save(a6);

			Agendamento a7 = new Agendamento();
			a7.setAgendado(false);
			a7.setProfissional(p2);
			a7.setData(dF.parse("19-09-2022"));
			a7.setHora(tF.parse("12:00:00"));
			agendamentoDAO.save(a7);
		};
		
	}
}
