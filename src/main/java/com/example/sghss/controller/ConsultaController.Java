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

import com.example.sghss.bo.ConsultaBO;
import com.example.sghss.bo.PacienteBO;
import com.example.sghss.bo.ProfissionalBO;
import com.example.sghss.model.Consulta;
import com.example.sghss.model.Especialidade;
import com.example.sghss.model.Paciente;
import com.example.sghss.model.Profissional;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaBO bo;

    @Autowired
    private PacienteBO pacienteBO;

    @Autowired
    private ProfissionalBO profissionalBO;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public String novo(ModelMap model) { 
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());
        model.addAttribute("especialidades", Especialidade.values());
        return "consulta/formulario";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String salvar(@Valid @ModelAttribute("consulta") Consulta consulta,
            BindingResult result,
            RedirectAttributes attr,
            ModelMap model) {

        if (consulta.getPaciente() == null) {
            result.rejectValue("paciente", "required", "Escolha um paciente");
        }

        if (result.hasErrors()) {
            model.addAttribute("pacientes", pacienteBO.lista());
            model.addAttribute("profissionais", profissionalBO.lista());
            model.addAttribute("especialidades", Especialidade.values());
            return "consulta/formulario";
        }

        Paciente paciente = pacienteBO.pesquisarPeloId(consulta.getPaciente().getId());
        consulta.setPaciente(paciente);
        
        Profissional profissional = profissionalBO.pesquisarPeloId(consulta.getProfissional().getId());
        consulta.setProfissional(profissional);

        if (consulta.getId() == null) {
            bo.create(consulta);
            attr.addFlashAttribute("feedback", "Consulta agendada!");
        } else {
            bo.update(consulta);
            attr.addFlashAttribute("feedback", "Consulta atualizada!");
        }

        return "redirect:/consultas";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String lista(ModelMap model) {
        model.addAttribute("consultas", bo.lista());
        return "consulta/lista";
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable Long id, ModelMap model) {
        model.addAttribute("consulta", bo.pesquisarPeloId(id));
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());
        model.addAttribute("especialidades", Especialidade.values());
        return new ModelAndView("/consulta/formulario", model);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/consultas";
    }
}
