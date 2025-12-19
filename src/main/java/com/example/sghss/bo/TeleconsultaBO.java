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
    private ProfissionalDAO profissionalDao; // Injeção necessária para buscar médicos

    // --- MÉTODOS QUE VOCÊ JÁ TINHA ---

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

    // --- MÉTODOS NOVOS COM A LÓGICA DE NEGÓCIO ---

    /**
     * Passo 1: Busca profissionais da especialidade e filtra quem está livre no horário
     */
    public List<Profissional> buscarMedicosDisponiveis(Especialidade especialidade, LocalDateTime dataHora) {
        // Usa aquele método que você já tem no ProfissionalDAO
        List<Profissional> medicosDaEspecialidade = profissionalDao.buscarPorEspecialidade(especialidade);

        // Filtra a lista: só mantém o médico se NÃO houver consulta para ele naquele horário
        return medicosDaEspecialidade.stream()
            .filter(medico -> !dao.existsPorMedicoEHorario(medico.getId(), dataHora))
            .collect(Collectors.toList());
    }

    /**
     * Passo 2: Cria a teleconsulta com a URL da sala automática
     */
    /**
     * Passo 2: Cria a teleconsulta com a URL da sala automática
     */
    @Transactional
    public void create(Teleconsulta teleconsulta) {
        // --- NOVA REGRA: Validar se a data é no passado ---
        if (teleconsulta.getData().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Não é possível agendar uma consulta para uma data ou horário que já passou.");
        }

        // Validação de segurança: o horário está mesmo livre?
        if (dao.existsPorMedicoEHorario(teleconsulta.getProfissional().getId(), teleconsulta.getData())) {
            throw new RuntimeException("Desculpe, este médico acabou de ser ocupado neste horário.");
        }

        // Regra de Negócio: Gerar link da sala automaticamente
        String codigoSala = UUID.randomUUID().toString().substring(0, 8);
        teleconsulta.setUrlSala("https://meet.jit.si/sghss-" + codigoSala);

        // Agora sim, envia para o DAO salvar
        dao.create(teleconsulta);
    }
    
}