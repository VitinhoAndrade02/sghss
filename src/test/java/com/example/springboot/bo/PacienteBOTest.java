package com.example.springboot.bo;
import com.example.sghss.bo.PacienteBO;
import com.example.sghss.model.Paciente;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;  
import com.example.sghss.model.Sexo;



@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PacienteBOTest {
    @Autowired
    private PacienteBO bo;

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
    }

    @Test
    @Order(2)
    public void pesquisarPeloIdTest() {
        Paciente paciente = bo.pesquisarPeloId(1L);
        System.out.println(paciente);
    }

    @Test
    @Order(3)
    public void listaTest() {
        List<Paciente> pacientes = bo.lista();
        for (Paciente paciente : pacientes) {
            System.out.println(paciente);
        }
    }

    @Test
    @Order(4)
    public void updateTest() {
        Paciente paciente = bo.pesquisarPeloId(1L);
        paciente.setTelefone("11888888888");
        bo.update(paciente);
    }

    @Test
    @Order(5)
    public void deleteTest() {
        bo.delete(1L);
    }

   

}
