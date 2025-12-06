package com.example.sghss.bo;
// package com.example.shgss.bo;

// import java.util.Scanner;
// import org.springframework.beans.factory.annotation.Autowired;
// import com.example.sghss.bo.PacienteBO;
// import com.example.sghss.bo.ProfissionalBO;
// import com.example.sghss.bo.UsuarioBO;
// import com.example.sghss.model.Paciente;
// import com.example.sghss.model.Profissional;
// import com.example.sghss.model.Usuario;

    
// public class UsuarioBOTest {

//     @Autowired
//     private UsuarioBO usuarioBO;

//     @Autowired
//     private PacienteBO pacienteBO;

//     @Autowired
//     private ProfissionalBO profissionalBO;

//     public void createUsuario() {

//         Scanner sc = new Scanner(System.in);

//         System.out.println("Criar usuário como (1) Paciente ou (2) Profissional?");
//         int opcao = sc.nextInt();
//         sc.nextLine(); // limpar buffer

//         Usuario usuario = new Usuario();
//         usuario.setNome("Victor Andrade");
//         usuario.setEmail("victor.andrade@example.com");
//         usuario.setSenha("senhaSegura123");

//         usuarioBO.create(usuario);  

//         if (opcao == 1) {
//             Paciente p = new Paciente();
//             p.setUsuario(usuario);
//             pacienteBO.create(p);
//             System.out.println("Paciente criado!");
//         } else if (opcao == 2) {
//             Profissional pr = new Profissional();
//             pr.setUsuario(usuario);
//             profissionalBO.create(pr);
//             System.out.println("Profissional criado!");
//         } else {
//             System.out.println("Opção inválida.");
//         }
//     }

//     public void PesquisarPeloId() {
//         Usuario usuario = usuarioBO.pesquisarPeloId(1L);
//         System.out.println(usuario);
//     }
// }
