package com.example.sghss.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.sghss.bo.ConsultaBO;
import com.example.sghss.model.Consulta;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaBO bo;

    @GetMapping("/novo")
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());
        return new ModelAndView("consulta/formulario", model);
    }

    @PostMapping("/novo")
    public String salvar(@ModelAttribute Consulta consulta) {
        bo.create(consulta);
        return "redirect:/consultas/lista";
    }

    @GetMapping("/lista")
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("consultas", bo.lista());
        return new ModelAndView("consulta/lista", model);
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id, ModelMap model) {
        model.addAttribute("consulta", bo.pesquisarPeloId(id));
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());
        return new ModelAndView("consulta/formulario", model);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/consultas/lista";
    }
}
