package com.example.sghss.bo;

import com.example.sghss.model.Paciente;
import com.example.sghss.model.Sexo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit; // NOVO IMPORT NECESSÁRIO
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteBOTest {

    @Autowired
    private PacienteBO pacienteBO;

    private static Long pacienteIdGlobal;

    // Métodos utilitários de criação de paciente (para testes limpos)
    private Paciente createNewPaciente(String cpf) {
        Paciente paciente = new Paciente();
        paciente.setNome("João da Silva Teste");
        paciente.setCpf(cpf); // CPF com 11 dígitos, como corrigido anteriormente
        paciente.setTelefone("11987654321");
        paciente.setEmail("joao.teste@mail.com");
        paciente.setSexo(Sexo.MASCULINO);
        paciente.setDataNascimento(LocalDate.of(1990, 1, 1));
        paciente.setEndereco("Rua dos Testes, 123");
        paciente.setHistoricoClinico("Sem histórico.");
        return paciente;
    }

    @Test
    @Order(1) // Executa primeiro: Cria e armazena o ID
    @Transactional
    @Commit // <<< CORREÇÃO: Força o commit da transação para persistir o paciente no banco
    void createTest() {
        Paciente paciente = createNewPaciente("12345678901");
        
        pacienteBO.create(paciente);
        
        // Armazena o ID gerado
        pacienteIdGlobal = paciente.getIdPaciente(); 
        
        System.out.println("Paciente criado com ID: " + pacienteIdGlobal);
        assertNotNull(pacienteIdGlobal, "O ID deve ser gerado após a criação.");
    }
    
    @Test
    @Order(2) // Executa em segundo: Pesquisa
    void pesquisarPeloIdTest() {
        // Assegura que o ID foi gerado no teste anterior
        assertNotNull(pacienteIdGlobal, "ID deve ter sido definido no teste anterior."); 

        Paciente encontrado = pacienteBO.pesquisarPeloId(pacienteIdGlobal);
        
        // Confirma se o paciente foi encontrado no BD
        assertNotNull(encontrado, "O paciente deve ser encontrado pelo ID."); 
        assertEquals("João da Silva Teste", encontrado.getNome());
    }

    @Test
    @Order(3) // Executa em terceiro: Lista
    void listaTest() {
        List<Paciente> pacientes = pacienteBO.lista();
        
        // A lista não pode estar vazia (pois o createTest rodou e fez commit)
        assertFalse(pacientes.isEmpty(), "A lista de pacientes não deve estar vazia."); 
    }

    @Test
    @Order(4) // Executa em quarto: Atualiza
    void updateTest() {
        assertNotNull(pacienteIdGlobal, "ID deve ter sido definido no teste anterior.");
        
        // 1. Recupera o paciente usando o ID gerado
        Paciente paciente = pacienteBO.pesquisarPeloId(pacienteIdGlobal);

        // Verifica se o paciente foi encontrado antes de tentar manipulá-lo
        assertNotNull(paciente, "Paciente deve ser encontrado para ser atualizado."); 

        // Agora é seguro chamar setTelefone
        String novoTelefone = "11999998888";
        paciente.setTelefone(novoTelefone); 
        
        // 2. Chama o método de atualização
        pacienteBO.update(paciente);
        
        // 3. Busca o paciente novamente no banco de dados para confirmar a alteração.
        Paciente pacienteAtualizado = pacienteBO.pesquisarPeloId(pacienteIdGlobal);
        
        // 4. Verifica a atualização
        assertNotNull(pacienteAtualizado, "Paciente atualizado deve ser encontrado.");
        assertEquals(novoTelefone, pacienteAtualizado.getTelefone(), "O telefone deve ser atualizado.");
    }

    @Test
    @Order(5) // Executa por último: Deleta
    void deleteTest() {
        assertNotNull(pacienteIdGlobal, "ID deve ter sido definido no teste anterior.");

        // Deleta o paciente
        pacienteBO.delete(pacienteIdGlobal);

        System.out.println("Paciente deletado com ID: " + pacienteIdGlobal);

        // Confirma que ele não existe mais
        Paciente deletado = pacienteBO.pesquisarPeloId(pacienteIdGlobal);
        assertNull(deletado, "O paciente deve ser null após a deleção.");
    }
}