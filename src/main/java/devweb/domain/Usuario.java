package devweb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario extends AbstractEntity<Long>{

	@Column(nullable = true, unique = true, length = 14)
	@NotNull(message = "{NotNull.usuario.CPF}")
	private String CPF;

	@Column(nullable = true, unique = false, length = 40)
	@NotBlank(message = "{NotBlank.usuario.email}")
	private String email;

	@Column(nullable = true, unique = false, length = 40)
	@NotNull(message = "{NotNull.usuario.senha}")
	private String senha;

	@Column(nullable = true, unique = false, length = 40)
	@NotBlank(message = "{NotBlank.usuario.nome}")
	private String nome;

	@Column(nullable = true, unique = false)
	private Character sexo;

	@Column(nullable = true, unique = false, length = 15)
	private String telefone;

	@Column(nullable = true, unique = false, length = 50)
	private String data_nascimento;
	
	public Usuario() {
		this.CPF = null;
		this.nome = null;
		this.senha = null;
		this.sexo = null;
		this.telefone = null;
		this.data_nascimento = null;
	}

	public Usuario(String CPF, String email, String senha, String nome, Character sexo, 
			String telefone, String data_nascimento) {
        this.CPF = CPF;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.sexo = sexo;
        this.telefone= telefone;
        this.data_nascimento = data_nascimento;
    }
	
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cpf) {
		CPF = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Character getSexo() {
		return sexo;
	}
	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
}
