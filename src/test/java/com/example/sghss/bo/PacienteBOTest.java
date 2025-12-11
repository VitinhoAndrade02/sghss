package com.example.sghss.bo;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.sghss.model.Paciente;
import com.example.sghss.model.Sexo;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class PacienteBOTest {

    @Autowired
    private PacienteBO bo;

    @Test
    @Order(1)
    public void create() {
        Paciente paciente = new Paciente();
        paciente.setNome("João da Silva ");
        paciente.setCpf("12345678901");
        paciente.setTelefone("11987654321");
        paciente.setEmail("joao.silva@mail.com");
        paciente.setSexo(Sexo.MASCULINO);
        paciente.setDataNascimento(LocalDate.of(1990, 1, 1));
        paciente.setEndereco("Rua dos joao, 123");
        paciente.setHistoricoClinico("Sem histórico.");
        bo.create(paciente);
    }

    @Test
    @Order(2)
    public void pesquisarPeloId() {
        Paciente paciente = bo.pesquisarPeloId(1L);
        System.out.println(paciente);
    }
    
    @Test
    @Order(3)
    public void update() {
        Paciente paciente = bo.pesquisarPeloId(1L);
        paciente.setEndereco("Rua silva, 456");
        bo.update(paciente);
        
    }

    @Test
    @Order(4)
    public void lista() {
        for (Paciente paciente : bo.lista()) {
            System.out.println(paciente);
        }
    }

    @Test
    @Order(5)
    public void delete() {
        bo.delete(1L);
    }

}       