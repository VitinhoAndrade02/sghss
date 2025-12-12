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

import com.example.sghss.bo.UnidadeBO;
import com.example.sghss.bo.LeitoBO;
import com.example.sghss.model.Unidade;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/unidades")
public class UnidadeController {

    @Autowired
    private UnidadeBO bo;

    @Autowired
    private LeitoBO leitoBO;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("unidade", new Unidade());
        model.addAttribute("leitos", leitoBO.lista());
        return new ModelAndView("/unidade/formulario", model);
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public String salva(@Valid @ModelAttribute Unidade unidade,
        BindingResult result,
        RedirectAttributes attr) {

        if (result.hasErrors())
            return "unidade/formulario";

        if (unidade.getId() == null) {
            bo.create(unidade);
            attr.addFlashAttribute("feedback", "Unidade cadastrada com sucesso");
        } else {
            bo.update(unidade);
            attr.addFlashAttribute("feedback", "Unidade atualizada com sucesso");
        }

        return "redirect:/unidades";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("unidades", bo.lista());
        return new ModelAndView("/unidade/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable Long id, ModelMap model) {

        model.addAttribute("unidade", bo.pesquisarPeloId(id));
        model.addAttribute("leitos", leitoBO.lista());

        return new ModelAndView("/unidade/formulario", model);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/unidades";
    }
}
