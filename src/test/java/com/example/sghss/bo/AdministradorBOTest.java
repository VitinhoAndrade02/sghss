package com.example.sghss.bo;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.sghss.model.Administrador;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class AdministradorBOTest {
    
    @Autowired
    private AdministradorBO bo;

    @Test
    @Order(1)
    public void create(){
        Administrador administrador = new Administrador();
        administrador.setNome("Administrador Teste"); 
        administrador.setAtivo(true);  
        bo.create(administrador);
    }

    @Test
    @Order(2)
    public void pesquisarPeloId(){
        Administrador administrador = bo.pesquisarPeloId(1L);
        System.out.println(administrador);
    }

    @Test
    @Order(3)
    public void update(){
        Administrador administrador = bo.pesquisarPeloId(1L);
        administrador.setNome("Mario Junior");
        bo.update(administrador);
    }

}
