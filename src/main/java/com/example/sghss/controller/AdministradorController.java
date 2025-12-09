package com.example.sghss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.sghss.bo.AdministradorBO;
import com.example.sghss.model.Administrador;


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

    
}
