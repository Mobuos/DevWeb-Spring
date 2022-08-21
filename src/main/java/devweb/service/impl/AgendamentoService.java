package devweb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import devweb.domain.Agendamento;
import devweb.domain.Cliente;
import devweb.domain.Profissional;
import devweb.dao.IAgendamentoDAO;
import devweb.dao.IClienteDAO;
import devweb.dao.IProfissionalDAO;
import devweb.service.spec.IAgendamentoService;

@Service
@Transactional(readOnly = false)
public class AgendamentoService implements IAgendamentoService{
    @Autowired
    IAgendamentoDAO dao;

    @Autowired
    IClienteDAO cDao;

    @Autowired
    IProfissionalDAO pDao;

    @Transactional(readOnly = true)
    public List<Agendamento> buscarPorCliente(String cpf) {
        Cliente c = cDao.findByCPF(cpf);
        return dao.findAllByCliente(c);
    }

    @Transactional(readOnly = true)
    public List<Agendamento> buscarPorProfissional(String cpf) {
        Profissional p = pDao.findByCPF(cpf);
        return dao.findAllByProfissional(p);
    }

    public void salvar(Agendamento agendamento) {
        dao.save(agendamento);
    }

    public void excluir(Agendamento agendamento) {
        dao.delete(agendamento);
    }

    public Optional<Agendamento> buscarPorID(Long id) {
        return dao.findById(id);
    }

}
