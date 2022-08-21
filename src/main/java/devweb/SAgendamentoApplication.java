package devweb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import devweb.dao.IClienteDAO;
import devweb.dao.IProfissionalDAO;
// import devweb.domain.Admin;
// import devweb.domain.Profissional;
// import devweb.domain.Agendamento;
import devweb.domain.Cliente;
// import devweb.domain.Usuario;
import devweb.domain.Profissional;

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

			
			//Profissional p1 = new Profissional("12345678910","pro1@profissional.com", "pro", "profissional 1", 'm', "11987654321", "20/08/20222","areap1","especialidadep1","qualip1");
			//profissionalDAO.save(p1);
						
			// Editora e1 = new Editora();
			// e1.setCNPJ("55.789.390/0008-99");
			// e1.setNome("Companhia das Letras");
			// editoraDAO.save(e1);
			
			// Editora e2 = new Editora();
			// e2.setCNPJ("71.150.470/0001-40");
			// e2.setNome("Record");
			// editoraDAO.save(e2);
			
			// Editora e3 = new Editora();
			// e3.setCNPJ("32.106.536/0001-82");
			// e3.setNome("Objetiva");
			// editoraDAO.save(e3);
			
			// Livro l1 = new Livro();
			// l1.setTitulo("Ensaio sobre a Cegueira");
			// l1.setAutor("José Saramago");
			// l1.setAno(1995);
			// l1.setPreco(BigDecimal.valueOf(54.9));
			// l1.setEditora(e1);
			// livroDAO.save(l1);
			
			// Livro l2 = new Livro();
			// l2.setTitulo("Cem anos de Solidão");
			// l2.setAutor("Gabriel Garcia Márquez");
			// l2.setAno(1977);
			// l2.setPreco(BigDecimal.valueOf(59.9));
			// l2.setEditora(e2);
			// livroDAO.save(l2);
			
			// Livro l3 = new Livro();
			// l3.setTitulo("Diálogos Impossíveis");
			// l3.setAutor("Luis Fernando Verissimo");
			// l3.setAno(2012);
			// l3.setPreco(BigDecimal.valueOf(22.9));
			// l3.setEditora(e3);
			// livroDAO.save(l3);
		};
		
		
	}
}
