package com.example.sghss.bo;

import com.example.sghss.model.Paciente;
import com.example.sghss.model.Sexo;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PacienteBOTest {

    @Autowired
    private PacienteBO bo;

    // Armazena o ID do paciente criado para usar nos testes seguintes
    private static Long pacienteId;

    @Test
    @Order(1)
    public void createTest() {
        Paciente paciente = new Paciente();
        paciente.setNome("João Silva");
        paciente.setDataNascimento(LocalDate.of(2002, 7, 14));
        paciente.setSexo(Sexo.MASCULINO);
        paciente.setCpf("123.456.789-00");
        paciente.setEmail("joao.silva@example.com");
        paciente.setEndereco("Rua A, 123");
        paciente.setTelefone("11999999999");

        bo.create(paciente);
        pacienteId = paciente.getIdPaciente(); // pega o ID gerado
        System.out.println("Paciente criado com ID: " + pacienteId);
    }

    @Test
    @Order(2)
    public void pesquisarPeloIdTest() {
        Paciente paciente = bo.pesquisarPeloId(pacienteId);
        System.out.println("Paciente encontrado: " + pacienteInfo(paciente));
    }

    @Test
    @Order(3)
    public void listaTest() {
        List<Paciente> pacientes = bo.lista();
        System.out.println("Lista de pacientes:");
        for (Paciente paciente : pacientes) {
            System.out.println(pacienteInfo(paciente));
        }
    }

    @Test
    @Order(4)
    public void updateTest() {
        Paciente paciente = bo.pesquisarPeloId(pacienteId);
        paciente.setTelefone("11888888888");
        bo.update(paciente);
        System.out.println("Paciente atualizado: " + pacienteInfo(paciente));
    }

    @Test
    @Order(5)
    public void deleteTest() {
        bo.delete(pacienteId);
        System.out.println("Paciente deletado com ID: " + pacienteId);
    }

    // Método auxiliar para imprimir os dados do paciente de forma legível
    private String pacienteInfo(Paciente paciente) {
        if (paciente == null) return "Paciente não encontrado";
        return String.format("ID: %d | Nome: %s | CPF: %s | Telefone: %s | Email: %s | Sexo: %s",
                paciente.getIdPaciente(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEmail(),
                paciente.getSexo().getDescricao());
    }
}
