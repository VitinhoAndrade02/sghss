package com.example.sghss.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.sghss.bo.ProntuarioBO;
import com.example.sghss.model.Prontuario;
import com.example.sghss.bo.PacienteBO;
import com.example.sghss.bo.ProfissionalBO;


@Controller
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioBO bo;
    @Autowired
    private PacienteBO pacienteBO;
    @Autowired
    private ProfissionalBO profissionalBO;

    @GetMapping("/novo")
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("prontuario", new Prontuario());
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());
        return new ModelAndView("prontuario/formulario", model);
    }

    @PostMapping("/novo")
    public String salvar(@ModelAttribute Prontuario prontuario) {
        bo.create(prontuario);
        return "redirect:/prontuarios/lista";
    }

    @GetMapping("/lista")
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("prontuarios", bo.lista());
        return new ModelAndView("prontuario/lista", model);
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id, ModelMap model) {
        model.addAttribute("prontuario", bo.pesquisarPeloId(id));
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());
        return new ModelAndView("prontuario/formulario", model);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/prontuarios/lista";
    }
}
