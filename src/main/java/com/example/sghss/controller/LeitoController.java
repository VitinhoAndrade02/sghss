package com.example.sghss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.sghss.bo.LeitoBO;
import com.example.sghss.model.Leito;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/leitos")
public class LeitoController {

    @Autowired
    private LeitoBO bo;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("leito", new Leito());
        return new ModelAndView("/leito/formulario", model);
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)   
    public String salva(@Valid @ModelAttribute Leito leito, 
        BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) return "leito/formulario";

        if (leito.getId() == null) {
            bo.create(leito);
            attr.addFlashAttribute("feedback", "Leito cadastrado com sucesso");
        } else {
            bo.update(leito);
            attr.addFlashAttribute("feedback", "Leito atualizado com sucesso");
        }
        return "redirect:/leitos";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("leitos", bo.lista());
        return new ModelAndView("/leito/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable Long id, ModelMap model) {
        model.addAttribute("leito", bo.pesquisarPeloId(id));
        return new ModelAndView("/leito/formulario", model);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/leitos";
    }
}
