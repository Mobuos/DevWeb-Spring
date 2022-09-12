package devweb.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import devweb.domain.Usuario;

@Repository
public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{
    Usuario findByEmail(String id);
}
