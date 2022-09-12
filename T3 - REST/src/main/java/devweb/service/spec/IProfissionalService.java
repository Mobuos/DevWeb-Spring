package devweb.service.spec;

import java.util.List;

import devweb.domain.Profissional;

public interface IProfissionalService {
    
	Profissional buscarPorCPF(String cpf);

    List<Profissional> buscarTodos();
   
    List<Profissional> findByEspecialidade(String especialidade);

    void salvar(Profissional profissional);

    void excluir(String cpf);

	Profissional buscarPorId(Long id);

}
