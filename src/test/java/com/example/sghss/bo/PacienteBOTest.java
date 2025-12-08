package com.example.sghss.bo;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit; 
import org.springframework.transaction.annotation.Transactional;

import com.example.sghss.model.Paciente;
import com.example.sghss.model.Sexo;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteBOTest {

    @Autowired
    private PacienteBO pacienteBO;

    private static Long pacienteIdGlobal;

    private Paciente createNewPaciente(String cpf) {
        Paciente paciente = new Paciente();
        paciente.setNome("João da Silva Teste");
        paciente.setCpf(cpf);
        paciente.setTelefone("11987654321");
        paciente.setEmail("joao.teste@mail.com");
        paciente.setSexo(Sexo.MASCULINO);
        paciente.setDataNascimento(LocalDate.of(1990, 1, 1));
        paciente.setEndereco("Rua dos Testes, 123");
        paciente.setHistoricoClinico("Sem histórico.");
        return paciente;
    }

    @Test
    @Order(1)
    @Transactional
    @Commit
    void createTest() {
        Paciente paciente = createNewPaciente("12345678901");

        pacienteBO.create(paciente);

        pacienteIdGlobal = paciente.getIdPaciente();

        System.out.println("Paciente criado com ID: " + pacienteIdGlobal);
        assertNotNull(pacienteIdGlobal, "O ID deve ser gerado após a criação.");
    }
    
    @Test
    @Order(2)
    void pesquisarPeloIdTest() {
        assertNotNull(pacienteIdGlobal, "ID deve ter sido definido no teste anterior."); 

        Paciente encontrado = pacienteBO.pesquisarPeloId(pacienteIdGlobal);

        assertNotNull(encontrado, "O paciente deve ser encontrado pelo ID."); 
        assertEquals("João da Silva Teste", encontrado.getNome());
    }

    @Test
    @Order(3)
    void listaTest() {
        List<Paciente> pacientes = pacienteBO.lista();

        assertFalse(pacientes.isEmpty(), "A lista de pacientes não deve estar vazia."); 
    }

    @Test
    @Order(4)
    void updateTest() {
        assertNotNull(pacienteIdGlobal, "ID deve ter sido definido no teste anterior.");

        Paciente paciente = pacienteBO.pesquisarPeloId(pacienteIdGlobal);

        assertNotNull(paciente, "Paciente deve ser encontrado para ser atualizado."); 

        String novoTelefone = "11999998888";
        paciente.setTelefone(novoTelefone); 

        pacienteBO.update(paciente);

        Paciente pacienteAtualizado = pacienteBO.pesquisarPeloId(pacienteIdGlobal);

        assertNotNull(pacienteAtualizado, "Paciente atualizado deve ser encontrado.");
        assertEquals(novoTelefone, pacienteAtualizado.getTelefone(), "O telefone deve ser atualizado.");
    }

    @Test
    @Order(5)
    void deleteTest() {
        assertNotNull(pacienteIdGlobal, "ID deve ter sido definido no teste anterior.");

        pacienteBO.delete(pacienteIdGlobal);

        System.out.println("Paciente deletado com ID: " + pacienteIdGlobal);

        Paciente deletado = pacienteBO.pesquisarPeloId(pacienteIdGlobal);
        assertNull(deletado, "O paciente deve ser null após a deleção.");
    }
}