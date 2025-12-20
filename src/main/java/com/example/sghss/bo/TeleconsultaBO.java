package com.example.sghss.bo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sghss.dao.ProfissionalDAO;
import com.example.sghss.dao.TeleconsultaDAO; // Você precisará injetar o DAO de profissional
import com.example.sghss.model.Especialidade;
import com.example.sghss.model.Profissional;
import com.example.sghss.model.Teleconsulta;

@Service
public class TeleconsultaBO {

    @Autowired
    private TeleconsultaDAO dao;

    @Autowired
    private ProfissionalDAO profissionalDao; 


    public Teleconsulta pesquisarPeloId(Long id) {
        return dao.pesquisarPeloId(id);
    }

    public List<Teleconsulta> lista() {
        return dao.lista();
    }

    public void update(Teleconsulta teleconsulta) {
        dao.update(teleconsulta);
    }

    public void delete(Long id) {
        dao.delete(id);
    }

   
    public List<Profissional> buscarMedicosDisponiveis(Especialidade especialidade, LocalDateTime dataHora) {
        List<Profissional> medicosDaEspecialidade = profissionalDao.buscarPorEspecialidade(especialidade);

        return medicosDaEspecialidade.stream()
            .filter(medico -> !dao.existsPorMedicoEHorario(medico.getId(), dataHora))
            .collect(Collectors.toList());
    }

    @Transactional
    public void create(Teleconsulta teleconsulta) {
        if (teleconsulta.getData().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível agendar uma consulta para uma data ou horário que já passou.");
        }

        if (dao.existsPorMedicoEHorario(teleconsulta.getProfissional().getId(), teleconsulta.getData())) {
            throw new RuntimeException("Desculpe, este médico acabou de ser ocupado neste horário.");
        }

        String codigoSala = UUID.randomUUID().toString().substring(0, 8);
        teleconsulta.setUrlSala("https://meet.jit.si/sghss-" + codigoSala);

        dao.create(teleconsulta);
    }
    
}