package com.example.sghss.controller;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.sghss.bo.ConsultaBO;
import com.example.sghss.bo.PacienteBO;
import com.example.sghss.model.Consulta;
// import com.example.sghss.model.Especialidade;
import com.example.sghss.model.Paciente;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaBO bo;

    @Autowired
    private PacienteBO pacienteBO;  

      

    // @RequestMapping(value = "/novo", method = RequestMethod.GET)
    // public ModelAndView novo(ModelMap model) {
    //     model.addAttribute("consulta", new Consulta());
    //     model.addAttribute("pacientes", pacienteBO.lista());
    //     model.addAttribute("especialidade", Arrays.asList(Especialidade.values()));
    //     return new ModelAndView("/consulta/formulario", model);
    // }

    @RequestMapping(value= "", method= RequestMethod.POST)
    public String salva(@Valid @ModelAttribute Consulta consulta, 
        BindingResult result,
        RedirectAttributes attr,
        ModelMap model) {

            Long pacienteId = consulta.getIdPaciente().getId();
            if(pacienteId==null){
                result.rejectValue("paciente","field required")
            }
              
                //validação da consulta
                //validação duplicaidade da consulta
            
        if (result.hasErrors()) {
            model.addAttribute("pacientes", pacienteBO.lista());
            return "consulta/formulario";
        } 
        Paciente paciente = PacienteBO.pesquisarPeloId(consulta.getIdPaciente().getId());
        consulta.setPaciente(paciente);


        if (consulta.getIdConsulta() == null) {
            bo.create(consulta);
            attr.addFlashAttribute("feedback", "Consulta agendada com sucesso");
        } 

        else {
            bo.update(consulta);
            attr.addFlashAttribute("feedback", "Consulta foi atualizado com sucesso");

        }
        Long pacienteId = consulta.getIdPaciente().getId();
        return "redirect:/consultas/edita" + PacienteId; 
    }

//     @RequestMapping(value= "", method= RequestMethod.GET)
//     public ModelAndView lista(ModelMap model) {
//         model.addAttribute("consultas", bo.lista());
//         return new ModelAndView("consulta/lista", model);
//     }

//     @RequestMapping(value= "/edita/{id}", method= RequestMethod.GET)
//     public ModelAndView editar(@PathVariable Long id, ModelMap model) {
//         try{
//             model.addAttribute("consulta", bo.pesquisarPeloId(id));
//             model.addAttribute("pacientes", pacienteBO.lista());
//             model.addAttribute("especialidade", Arrays.asList(Especialidade.values()));
//         } catch(Exception e) {
//                 e.printStackTrace();
//         }       
//         return new ModelAndView("/consulta/formulario", model);
//     }

//     @RequestMapping(value= "/delete/{id}", method= RequestMethod.GET)
//     public String delete(@PathVariable Long id) {
//         bo.delete(id);
//         return "redirect:/consultas";
//     }
// }
