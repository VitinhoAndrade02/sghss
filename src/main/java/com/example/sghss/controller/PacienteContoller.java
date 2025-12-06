package com.example.sghss.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.sghss.bo.PacienteBO;
import com.example.sghss.model.Paciente;
import com.example.sghss.model.Sexo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/pacientes")
public class PacienteContoller {

    @Autowired
    private PacienteBO bo;

    @GetMapping("/novo")
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("sexos", Sexo.values());
        return new ModelAndView("paciente/formulario", model);
    }

    @PostMapping("/novo")
    public String salvar(@ModelAttribute Paciente paciente) {
        bo.create(paciente);
        return "redirect:/pacientes/lista";
    }

    @GetMapping("/lista")
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("pacientes", bo.lista());
        return new ModelAndView("paciente/lista", model);
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id, ModelMap model) {
        model.addAttribute("paciente", bo.pesquisarPeloId(id));
        model.addAttribute("sexos", Sexo.values());
        return new ModelAndView("paciente/formulario", model);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/pacientes/lista";
    }
}
