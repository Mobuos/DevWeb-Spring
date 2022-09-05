package devweb.domain;

import java.util.Date; // Tipo "Date"
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Agendamento")
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Cliente cliente;

	@OneToOne
	private Profissional profissional;

	@Column(nullable = true, unique = false, length = 40)
	private Boolean agendado = true;

	@Column(nullable = false, unique = false, length = 40)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data; /*pesquisar tipo adequado*/

	@Column(nullable = false, unique = false, length = 40)
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	private Date hora; /*pesquisar tipo adequado*/

	public Agendamento (){
		
	}

	public Long getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Profissional getProfissional() {
		return profissional;
	}
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	public Boolean getAgendado() {
		return agendado;
	}
	public void setAgendado(Boolean agendado){
		this.agendado = agendado;
	}
	public java.util.Date getData() {
		return data;
	}
	public void setData(java.util.Date data) {
		this.data = data;
	}
	public java.util.Date getHora() {
		return hora;
	}
	public void setHora(java.util.Date hora) {
		this.hora = hora;
	}
}
