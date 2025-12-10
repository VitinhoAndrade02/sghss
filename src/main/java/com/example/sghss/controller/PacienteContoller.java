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
import com.example.sghss.model.Paciente;
import com.example.sghss.model.Sexo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pacientes")
public class PacienteContoller {

    @Autowired
    private PacienteBO bo;

    @RequestMapping(value= "/novo", method= RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("sexos", Sexo.values()); //posso remover
        return new ModelAndView("/paciente/formulario", model);
    }

    @RequestMapping(value= "/novo", method= RequestMethod.POST)
    public String salva(@Valid @ModelAttribute Paciente paciente, 
        BindingResult result,
        RedirectAttributes attr) {
        if (result.hasErrors()) 
            return "paciente/formulario";
        

        if (paciente.getIdPaciente() == null) {
            bo.create(paciente);
            attr.addFlashAttribute("feedback", "Paciente foi cadastrado com sucesso");
        } 

        else {
            bo.update(paciente);
            attr.addFlashAttribute("feedback", "Paciente foi atualizado com sucesso");

        }
        return "redirect:/pacientes"; 
    }

    @RequestMapping(value= "", method= RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("pacientes", bo.lista());
        return new ModelAndView("/paciente/lista", model);
    }

    @RequestMapping(value= "/edita/{id}", method= RequestMethod.GET)
    public ModelAndView edita(@PathVariable ("id") Long id, ModelMap model) {
        try{
            model.addAttribute("paciente", bo.pesquisarPeloId(id));
        }catch(Exception e) {
            e.printStackTrace();
        }       
        return new ModelAndView("/paciente/formulario", model);
    }

    @RequestMapping(value="/inativa/{id}", method= RequestMethod.GET)
    public String inativa(@PathVariable("id") Long id, RedirectAttributes attr) {
    System.out.println(id);
    try {
        Paciente paciente = bo.pesquisarPeloId(id);
        bo.inativa(paciente);
        attr.addFlashAttribute("feedback", "Paciente foi inativdo com sucesso");
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    return "redirect:/pacientes";
    }

    @RequestMapping(value="/ativa/{id}", method= RequestMethod.GET)
    public String ativa(@PathVariable("id") Long id, RedirectAttributes attr) {
    System.out.println(id);
    try {
        Paciente paciente = bo.pesquisarPeloId(id);
        bo.ativa(paciente);
        attr.addFlashAttribute("feedback", "Paciente foi ativdo com sucesso");
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    return "redirect:/pacientes";
    }


}
