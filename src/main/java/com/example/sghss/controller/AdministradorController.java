package com.example.sghss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.sghss.bo.AdministradorBO;
import com.example.sghss.model.Administrador;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorBO bo;

    @RequestMapping(value="/novo", method= RequestMethod.GET)
    public ModelAndView novo (ModelMap model) {
        model.addAttribute("administrador", new Administrador());
        return new ModelAndView("/administrador/formulario", model);
    }

    @RequestMapping(value= "", method= RequestMethod.POST)
    public String salvar(@Valid @ModelAttribute Administrador administrador, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) 
            return "administrador/formulario";

        if (administrador.getIdAdministrador() == null) {
            bo.create(administrador);
            
        } 

        else {
            bo.update(administrador);
        }
        return "redirect:/administradores";
    }

    @RequestMapping(value="", method= RequestMethod.GET)
    public ModelAndView lista (ModelMap model) {
        model.addAttribute("administradores", bo.lista());
        return new ModelAndView("/administrador/lista", model);
    }

    @RequestMapping(value="/edita/{id}", method= RequestMethod.GET)
    public ModelAndView edita (@PathVariable("id") Long id, ModelMap model) {
        Administrador administrador = bo.pesquisarPeloId(id);
        return new ModelAndView("/administrador/formulario", model);
    }

    @RequestMapping(value="/inativa/{id}", method= RequestMethod.GET)
    public String inativa (@PathVariable("id") Long id) {
        Administrador administrador = bo.pesquisarPeloId(id);
        bo.inativa(administrador);
        return "redirect:/administradores";
    }

    @RequestMapping(value="/ativa/{id}", method= RequestMethod.GET)
    public String ativa (@PathVariable("id") Long id) {
        Administrador administrador = bo.pesquisarPeloId(id);
        bo.ativa(administrador);
        return "redirect:/administradores";
    }

}
    