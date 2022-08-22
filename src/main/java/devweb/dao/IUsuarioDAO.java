package devweb.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import devweb.domain.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{
    Usuario findByEmail(String id);
}
