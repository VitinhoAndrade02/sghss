package com.example.sghss.controller;

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

import com.example.sghss.bo.TeleconsultaBO;
import com.example.sghss.bo.PacienteBO;
import com.example.sghss.bo.ProfissionalBO;
import com.example.sghss.model.Teleconsulta;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/teleconsultas")
public class TeleconsultaController {

    @Autowired
    private TeleconsultaBO bo;

    @Autowired
    private PacienteBO pacienteBO;

    @Autowired
    private ProfissionalBO profissionalBO;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("teleconsulta", new Teleconsulta());
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());
        return new ModelAndView("/teleconsulta/formulario", model);
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public String salva(@Valid @ModelAttribute Teleconsulta teleconsulta,
        BindingResult result,
        RedirectAttributes attr) {

        if (result.hasErrors())
            return "teleconsulta/formulario";

        if (teleconsulta.getId() == null) {
            bo.create(teleconsulta);
            attr.addFlashAttribute("feedback", "Teleconsulta cadastrada com sucesso");
        } else {
            bo.update(teleconsulta);
            attr.addFlashAttribute("feedback", "Teleconsulta atualizada com sucesso");
        }

        return "redirect:/teleconsultas";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("teleconsultas", bo.lista());
        return new ModelAndView("/teleconsulta/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable Long id, ModelMap model) {

        model.addAttribute("teleconsulta", bo.pesquisarPeloId(id));
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());

        return new ModelAndView("/teleconsulta/formulario", model);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/teleconsultas";
    }
}
