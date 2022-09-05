package devweb.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import devweb.domain.Cliente;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, String>{
    Cliente findByCPF(String cpf);

    Cliente findById(Long id);

    List<Cliente> findAll();

    Cliente save(Cliente cliente);

    void deleteByCPF(String cpf);
}
