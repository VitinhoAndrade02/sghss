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
import com.example.sghss.model.Paciente;
import com.example.sghss.model.Profissional;
import com.example.sghss.model.StatusConsulta; 

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

    // LISTAGEM: Agora com a barra correta
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String lista(ModelMap model) {
        System.out.println(">>>> O CONTROLLER ESTA FUNCIONANDO!");
        model.addAttribute("consultas", bo.lista());
        return "/consulta/lista"; 
    }

    // FORMULÁRIO NOVO
    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        model.addAttribute("consulta", new Consulta());
        carregarCombos(model);
        return "/consulta/formulario";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String salvar(
            @Valid @ModelAttribute("consulta") Consulta consulta,
            BindingResult result,
            RedirectAttributes attr,
            ModelMap model) {

        // Se o Spring encontrar erros de validação (ex: campos vazios)
        if (result.hasErrors()) {
            carregarCombos(model);
            return "/consulta/formulario"; // Ajustado com "/"
        }

        // Lógica de Salvar/Update
        if (consulta.getId() == null) {
            bo.create(consulta);
            attr.addFlashAttribute("feedback", "Consulta agendada com sucesso!");
        } else {
            bo.update(consulta);
            attr.addFlashAttribute("feedback", "Consulta atualizada com sucesso!");
        }

        return "redirect:/consultas";
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable Long id, ModelMap model) {
        Consulta consulta = bo.pesquisarPeloId(id);
        model.addAttribute("consulta", consulta);
        carregarCombos(model);
        return new ModelAndView("/consulta/formulario", model);
    }

    private void carregarCombos(ModelMap model) {
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());
        model.addAttribute("especialidades", Especialidade.values());
        model.addAttribute("tiposConsulta", TipoConsulta.values());
        // Se você tiver um Enum de Status, adicione aqui também:
        model.addAttribute("statusConsulta", StatusConsulta.values());
    }
}