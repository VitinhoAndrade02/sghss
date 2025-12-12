package com.example.sghss.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.sghss.bo.ConsultaBO;
import com.example.sghss.dao.ConsultaDAO;
import com.example.sghss.model.Consulta;
import com.example.sghss.bo.PacienteBO;
import com.example.sghss.model.Paciente;



import jakarta.validation.Valid;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaBO bo;

    @Autowired
    private PacienteBO pacienteBO;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {

        model.addAttribute("consulta", new Consulta());
        model.addAttribute("pacientes", pacienteBO.lista());

        return "consulta/formulario";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String salvar(
            @Valid @ModelAttribute("consulta") Consulta consulta,
            BindingResult result,
            RedirectAttributes attr,
            ModelMap model) {

        if (consulta.getPaciente() == null || consulta.getPaciente().getId() == null) {
            result.rejectValue("paciente", "required", "Escolha um paciente");
        }

        if (result.hasErrors()) {
            model.addAttribute("pacientes", pacienteBO.lista());
            return "consulta/formulario";
        }

        Paciente paciente = pacienteBO.pesquisarPeloId(consulta.getPaciente().getId());
        consulta.setPaciente(paciente);

        if (consulta.getId() == null) {
            bo.create(consulta);
            attr.addFlashAttribute("feedback", "Consulta agendada!");
        } else {
            bo.update(consulta);
            attr.addFlashAttribute("feedback", "Consulta atualizada!");
        }

        return "redirect:/consultas";
    }
}
