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

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pacientes")
public class PacienteContoller {

    @Autowired
    private PacienteBO bo;

    @RequestMapping(value= "/novo", method= RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("paciente", new Paciente());
        return new ModelAndView("/paciente/formulario", model);
    }

    @RequestMapping(value= "/novo", method= RequestMethod.POST)
    public String salva(@Valid @ModelAttribute Paciente paciente, 
        BindingResult result,
        RedirectAttributes attr) {
        if (result.hasErrors()) 
            return "paciente/formulario";
        

        if (paciente.getId() == null) {
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

   @RequestMapping(value= "/delete/{id}", method= RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/pacientes";
    }


}
