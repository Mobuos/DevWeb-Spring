package devweb.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import devweb.domain.Profissional;

@SuppressWarnings("unchecked")
@Repository
public interface IProfissionalDAO extends CrudRepository<Profissional, String>{
    Profissional findByCPF(String cpf);

    List<Profissional> findAll();

    Profissional save(Profissional profissional);

    void deleteByCPF(String cpf);
}
