package devweb.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin extends Usuario{
    
    public Admin(String cpf,
                 String nome, 
                 String senha, 
                 String email, 
                 Character sexo, 
                 String telefone, 
                 String data_nascimento
    ) {
	    super(cpf, email, senha, nome, sexo, telefone, data_nascimento);
    }

}
