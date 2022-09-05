package devweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import devweb.domain.Cliente;
import devweb.dao.IClienteDAO;
import devweb.service.spec.IClienteService;

@Service
@Transactional(readOnly = false)
public class ClienteService implements IClienteService{

    @Autowired
    IClienteDAO dao;

    public void salvar(Cliente cliente) {
        dao.save(cliente);
    }

    public void excluir(String cpf) {
        dao.deleteByCPF(cpf);
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorCPF(String cpf) {
        return dao.findByCPF(cpf);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarTodos() {
        return dao.findAll();
    }

    // @Transactional(readOnly = true)
    // public boolean clienteTemAgendamentos(String cpf) {
    //     return !dao.findByCPF(cpf).getAgendamentos().isEmpty()
    // }
    
}
