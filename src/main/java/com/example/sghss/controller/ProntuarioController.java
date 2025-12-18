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

import com.example.sghss.bo.PacienteBO;
import com.example.sghss.bo.ProfissionalBO;
import com.example.sghss.bo.ProntuarioBO;
import com.example.sghss.model.Prontuario;
import com.example.sghss.model.Profissional;


import jakarta.validation.Valid;


@Controller
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioBO bo;
    
    @Autowired
    private PacienteBO pacienteBO;
  
    @Autowired
    private ProfissionalBO profissionalBO;    
    

    @RequestMapping(value = "/novo/{profissionalId}", method = RequestMethod.GET)
    public ModelAndView novo(@PathVariable Long profissionalId, ModelMap model) {
        Profissional profissional = profissionalBO.pesquisarPeloId(profissionalId);

        Prontuario prontuario = new Prontuario();
        prontuario.setProfissional(profissional);

        model.addAttribute("prontuario", prontuario);
        model.addAttribute("profissional", profissional);
        model.addAttribute("pacientes", pacienteBO.lista());

        return new ModelAndView("/prontuario/formulario", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable Long id, ModelMap model) {
        Prontuario prontuario = bo.pesquisarPeloId(id);
        Profissional profissional = prontuario.getProfissional();

        model.addAttribute("prontuario", prontuario);
        model.addAttribute("profissional", profissional);
        model.addAttribute("pacientes", pacienteBO.lista());

        return new ModelAndView("/prontuario/formulario", model);
    }



    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvar(@Valid @ModelAttribute Prontuario prontuario,
                        BindingResult result,
                        ModelMap model,
                        RedirectAttributes attr) {

        if (result.hasErrors()) {
            model.addAttribute("profissional", prontuario.getProfissional());
            model.addAttribute("pacientes", pacienteBO.lista());
            return "/prontuario/formulario";
        }

        prontuario.setPaciente(
            pacienteBO.pesquisarPeloId(prontuario.getPaciente().getId())
        );
        prontuario.setProfissional(
            profissionalBO.pesquisarPeloId(prontuario.getProfissional().getId())
        );

        if (prontuario.getId() == null) {
            bo.create(prontuario);
            attr.addFlashAttribute("feedback", "Prontuario cadastrada com sucesso");
        } else {
            bo.update(prontuario);
            attr.addFlashAttribute("feedback", "Prontuario atualizada com sucesso");
        }

        return "redirect:/prontuarios/profissional/" + prontuario.getProfissional().getId();
    }


    @RequestMapping(value = "/profissional/{profissionalId}", method = RequestMethod.GET)
    public ModelAndView listaPorProfissional(@PathVariable Long profissionalId, ModelMap model) {
        Profissional profissional = profissionalBO.pesquisarPeloId(profissionalId);
        model.addAttribute("profissional", profissional);
        model.addAttribute("prontuarios", bo.listaPorProfissional(profissionalId));
        return new ModelAndView("/prontuario/lista", model);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, RedirectAttributes attr) {
        Prontuario prontuario = bo.pesquisarPeloId(id);
        Long profissionalId = prontuario.getProfissional().getId();
        bo.delete(id);
        attr.addFlashAttribute("feedback", "Prontuario excluída com sucesso");
        return "redirect:/prontuarios/profissional/" + profissionalId;
    }
}