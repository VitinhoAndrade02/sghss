package com.example.sghss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.sghss.bo.PacienteBO;
import com.example.sghss.bo.TeleconsultaBO;
import com.example.sghss.model.Especialidade;
import com.example.sghss.model.Profissional;
import com.example.sghss.model.Teleconsulta;

@Controller
@RequestMapping("/teleconsulta")
public class TeleconsultaController {

    @Autowired
    private TeleconsultaBO bo;

    @Autowired
    private PacienteBO pacienteBo; 

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public String formulario(Model model) {
        model.addAttribute("teleconsulta", new Teleconsulta());
        model.addAttribute("especialidades", Especialidade.values());
        model.addAttribute("pacientes", pacienteBo.lista());
        return "teleconsulta/formulario"; 
    }

    @RequestMapping(value = "", method = RequestMethod.GET) 
    public String listarTodas(Model model) {
        model.addAttribute("consultas", bo.lista());
        return "teleconsulta/lista_geral"; 
    }

    @RequestMapping(value = "/disponibilidade", method = RequestMethod.POST)
    public String buscarDisponibilidade(Teleconsulta teleconsulta, Model model) {
        List<Profissional> disponiveis = bo.buscarMedicosDisponiveis(
            teleconsulta.getProfissional().getEspecialidade(), 
            teleconsulta.getData()
        );
        
        model.addAttribute("lista", disponiveis);
        model.addAttribute("teleconsulta", teleconsulta); 
        return "teleconsulta/lista";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvar(Teleconsulta teleconsulta, RedirectAttributes attr) {
        try {
            bo.create(teleconsulta);
            attr.addFlashAttribute("feedback", "Teleconsulta agendada com sucesso!");
            return "redirect:/teleconsulta"; 
        } catch (RuntimeException e) {
            attr.addFlashAttribute("erro", e.getMessage());
            return "redirect:/teleconsulta/novo";
        }
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            bo.delete(id);
            attr.addFlashAttribute("feedback", "Teleconsulta cancelada com sucesso!");
        } catch (Exception e) {
            attr.addFlashAttribute("erro", "Não foi possível cancelar a consulta.");
        }
        return "redirect:/teleconsulta";
    }
}