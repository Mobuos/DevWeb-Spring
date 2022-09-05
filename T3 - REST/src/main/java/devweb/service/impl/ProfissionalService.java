package devweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import devweb.domain.Profissional;
import devweb.dao.IProfissionalDAO;
import devweb.service.spec.IProfissionalService;

@Service
@Transactional(readOnly = false)
public class ProfissionalService implements IProfissionalService{

    @Autowired
    IProfissionalDAO dao;

    public void salvar(Profissional profissional) {
        dao.save(profissional);
    }

    public void excluir(String cpf) {
        dao.deleteByCPF(cpf);
    }

    @Transactional(readOnly = true)
    public Profissional buscarPorCPF(String cpf) {
        return dao.findByCPF(cpf);
    }

    @Transactional(readOnly = true)
    public List<Profissional> buscarTodos() {
        return dao.findAll();
    } 
}
