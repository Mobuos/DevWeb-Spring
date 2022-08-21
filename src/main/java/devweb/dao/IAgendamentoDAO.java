package devweb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import devweb.domain.Agendamento;
import devweb.domain.Cliente;
import devweb.domain.Profissional;

@SuppressWarnings("unchecked")
public interface IAgendamentoDAO extends CrudRepository<Agendamento, Long>{
    List<Agendamento> findAllByCliente(Cliente cliente);

    List<Agendamento> findAllByProfissional(Profissional profissional);

    Agendamento save(Agendamento agendamento);

    void delete(Agendamento agendamento);

    Optional<Agendamento> findById(Long id);
}
