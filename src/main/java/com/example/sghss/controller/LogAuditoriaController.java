package com.example.sghss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.sghss.bo.LogAuditoriaBO;

@Controller
@RequestMapping("/logs")
public class LogAuditoriaController {

    @Autowired
    private LogAuditoriaBO bo;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("logs", bo.lista());
        // Certifique-se de que o arquivo HTML estará em: src/main/resources/templates/log/lista.html
        return new ModelAndView("/logAuditoria/lista", model);
    }
}