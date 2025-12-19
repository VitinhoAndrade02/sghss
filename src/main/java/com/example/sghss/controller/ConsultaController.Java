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
import com.example.sghss.model.TipoConsulta;

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
        carregarCombos(model);
        return "consulta/formulario";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String salvar(
            @Valid @ModelAttribute("consulta") Consulta consulta,
            BindingResult result,
            RedirectAttributes attr,
            ModelMap model) {

        // Valida paciente
        if (consulta.getPaciente() != null && consulta.getPaciente().getId() != null) {
            Paciente paciente = pacienteBO.pesquisarPeloId(consulta.getPaciente().getId());
            if (paciente == null) {
                result.rejectValue("paciente", "notfound", "Paciente não encontrado");
            } else {
                consulta.setPaciente(paciente);
            }
        } else {
            result.rejectValue("paciente", "required", "Escolha um paciente");
        }

        // Valida profissional
        if (consulta.getProfissional() != null && consulta.getProfissional().getId() != null) {
            Profissional profissional = profissionalBO.pesquisarPeloId(consulta.getProfissional().getId());
            if (profissional == null) {
                result.rejectValue("profissional", "notfound", "Profissional não encontrado");
            } else {
                consulta.setProfissional(profissional);
            }
        } else {
            result.rejectValue("profissional", "required", "Escolha um profissional");
        }

        // Valida tipo de consulta
        if (consulta.getTipoConsulta() == null) {
            consulta.setTipoConsulta(TipoConsulta.PRESENCIAL); // default
        }

        // Valida status
        if (consulta.getStatusConsulta() == null) {
            consulta.setStatusConsulta(StatusConsulta.AGENDADA); // default
        }

        // Valida especialidade
        if (consulta.getEspecialidade() == null) {
            result.rejectValue("especialidade", "required", "Informe a especialidade");
        }

        if (result.hasErrors()) {
            carregarCombos(model);
            return "consulta/formulario";
        }

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
        carregarCombos(model);
        return new ModelAndView("consulta/formulario", model);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, RedirectAttributes attr) {
        bo.delete(id);
        attr.addFlashAttribute("feedback", "Consulta removida!");
        return "redirect:/consultas";
    }

    private void carregarCombos(ModelMap model) {
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());
        model.addAttribute("especialidades", Especialidade.values());
        model.addAttribute("tiposConsulta", TipoConsulta.values());
    }
}
