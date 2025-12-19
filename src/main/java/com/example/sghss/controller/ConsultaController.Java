package com.example.sghss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.sghss.bo.ConsultaBO;
import com.example.sghss.bo.PacienteBO;
import com.example.sghss.bo.ProfissionalBO;
import com.example.sghss.model.Consulta;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired private ConsultaBO bo;
    @Autowired private PacienteBO pacienteBo;
    @Autowired private ProfissionalBO profissionalBo;

    @GetMapping("")
    public String listar(Model model) {
        model.addAttribute("consultas", bo.lista());
        return "consulta/lista"; 
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("pacientes", pacienteBo.lista());
        model.addAttribute("profissionais", profissionalBo.lista());
        return "consulta/formulario"; 
    }

    @PostMapping("/salvar")
    public String salvar(Consulta consulta, RedirectAttributes attr) {
        try {
            bo.create(consulta);
            attr.addFlashAttribute("feedback", "Consulta agendada com sucesso!");
            return "redirect:/consultas";
        } catch (Exception e) {
            attr.addFlashAttribute("erro", "Erro ao salvar: " + e.getMessage());
            return "redirect:/consultas/novo";
        }
    }

    @GetMapping("/delete/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        bo.delete(id);
        attr.addFlashAttribute("feedback", "Consulta removida!");
        return "redirect:/consultas";
    }
}