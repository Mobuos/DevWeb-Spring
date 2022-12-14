package devweb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import devweb.domain.Agendamento;
import devweb.domain.Cliente;
import devweb.domain.Profissional;

@SuppressWarnings("unchecked")
@Repository
public interface IAgendamentoDAO extends CrudRepository<Agendamento, Long>{
    List<Agendamento> findAllByCliente(Cliente cliente);

    List<Agendamento> findAllByProfissional(Profissional profissional);
    
    List<Agendamento> findAllByProfissional(Long id);

    Agendamento save(Agendamento agendamento);

    void delete(Agendamento agendamento);

    Optional<Agendamento> findById(Long id);
}
