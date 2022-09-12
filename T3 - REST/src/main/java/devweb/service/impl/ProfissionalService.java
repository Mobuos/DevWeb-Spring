package devweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import devweb.domain.Agendamento;
import devweb.domain.Cliente;
import devweb.domain.Profissional;
import devweb.dao.IAgendamentoDAO;
import devweb.dao.IProfissionalDAO;
import devweb.service.spec.IProfissionalService;

@Service
@Transactional(readOnly = false)
public class ProfissionalService implements IProfissionalService{

    @Autowired
    IProfissionalDAO dao;

    @Autowired
    IAgendamentoDAO aDao;

    public void salvar(Profissional profissional) {
        dao.save(profissional);
    }

    public void excluir(String cpf) {
        List<Agendamento> agendamentos = aDao.findAllByProfissional(dao.findByCPF(cpf));
        agendamentos.forEach((agendamento) -> aDao.delete(agendamento));
        dao.deleteByCPF(cpf);
    }

    @Transactional(readOnly = true)
    public Profissional buscarPorCPF(String cpf) {
        return dao.findByCPF(cpf);
    }
    
    @Override
    public Profissional buscarPorId(Long id) {
        return dao.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Profissional> findByEspecialidade(String especialidade) {
        return dao.findByEspecialidade(especialidade);
    }
  
    @Transactional(readOnly = true)
    public List<Profissional> buscarTodos() {
        return dao.findAll();
    }

	
}
