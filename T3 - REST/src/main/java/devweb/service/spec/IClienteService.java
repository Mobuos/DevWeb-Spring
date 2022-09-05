package devweb.service.spec;

import java.util.List;

import devweb.domain.Cliente;

public interface IClienteService {
    Cliente buscarPorCPF(String cpf);

    Cliente buscarPorId(Long id);

    List<Cliente> buscarTodos();

    void salvar(Cliente cliente);

    void excluir(String cpf);

    // boolean clienteTemAgendamentos(String cpf)
}
