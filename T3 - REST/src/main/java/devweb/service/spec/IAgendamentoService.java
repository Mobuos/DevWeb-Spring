package devweb.service.spec;

import java.util.List;
import java.util.Optional;

import devweb.domain.Agendamento;

public interface IAgendamentoService {
    List<Agendamento> buscarPorCliente(String cpf);

    List<Agendamento> buscarPorProfissional(String cpf);

    List<Agendamento> buscarDisponivelPorProfissional(String cpf);

    void salvar(Agendamento agendamento);

    void excluir(Agendamento agendamento);

    Optional<Agendamento> buscarPorID(Long id);
    
    List<Agendamento> buscaTodos();
}
