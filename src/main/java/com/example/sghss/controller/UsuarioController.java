package com.example.sghss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.sghss.bo.UsuarioBO;
import com.example.sghss.model.Usuario;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;    
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioBO bo;   

    @RequestMapping(value="/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("usuario", new Usuario());
        return new ModelAndView("usuario/formulario", model);
    }   


}
