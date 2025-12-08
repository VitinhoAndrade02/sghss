package com.example.sghss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.sghss.bo.UsuarioBO;
import com.example.sghss.model.Usuario;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioBO bo;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("usuario", new Usuario());
        return new ModelAndView("usuario/formulario", model);
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public String salvar(@ModelAttribute Usuario usuario) {
        bo.create(usuario);
        return "redirect:/usuarios/lista";
    }

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("usuarios", bo.lista());
        return new ModelAndView("usuario/lista", model);
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public ModelAndView editar(@PathVariable Long id, ModelMap model) {
        model.addAttribute("usuario", bo.pesquisarPeloId(id));
        return new ModelAndView("usuario/formulario", model);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/usuarios/lista";
    }
}
