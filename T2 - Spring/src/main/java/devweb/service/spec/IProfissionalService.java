package devweb.service.spec;

import java.util.List;

import devweb.domain.Profissional;

public interface IProfissionalService {
    Profissional buscarPorCPF(String cpf);

    List<Profissional> buscarTodos();

    void salvar(Profissional profissional);

    void excluir(String cpf);

}
