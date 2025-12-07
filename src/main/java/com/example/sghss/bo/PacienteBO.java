// Conteúdo da classe com.example.sghss.bo.PacienteBO
package com.example.sghss.bo;

import com.example.sghss.dao.PacienteDAO;
import com.example.sghss.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List; // Importação necessária

@Service
public class PacienteBO {

    @Autowired
    private PacienteDAO dao; // Injeta o DAO

    @Transactional
    public void create(Paciente paciente) {
        dao.create(paciente);
    }
    
    // Novo método adicionado para corrigir o erro de compilação
    public Paciente pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }

    // NOVO MÉTODO: Implementação do lista() para ser chamado pelo teste
    public List<Paciente> lista() {
        return dao.lista();
    }
    
    @Transactional
    public void update(Paciente paciente) {
        dao.update(paciente);
    }
    
    @Transactional
    public void delete(Long id) {
        // O BO chama o método delete do DAO, que por sua vez chama o pesquisarPeloId.
        dao.delete(id);
    }
}