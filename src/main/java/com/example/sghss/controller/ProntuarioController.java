package com.example.sghss.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.sghss.bo.PacienteBO;
import com.example.sghss.bo.ProfissionalBO;
import com.example.sghss.bo.ProntuarioBO;
import com.example.sghss.model.Prontuario;


@Controller
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioBO bo;
    @Autowired
    private PacienteBO pacienteBO;
    @Autowired
    private ProfissionalBO profissionalBO;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("prontuario", new Prontuario());
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());
        return new ModelAndView("prontuario/formulario", model);
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public String salvar(@ModelAttribute Prontuario prontuario) {
        bo.create(prontuario);
        return "redirect:/prontuarios/lista";
    }

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("prontuarios", bo.lista());
        return new ModelAndView("prontuario/lista", model);
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public ModelAndView editar(@PathVariable Long id, ModelMap model) {
        model.addAttribute("prontuario", bo.pesquisarPeloId(id));
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());
        return new ModelAndView("prontuario/formulario", model);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/prontuarios/lista";
    }
}
