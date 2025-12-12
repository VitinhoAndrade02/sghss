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

import com.example.sghss.bo.RelFinanceiroBO;
import com.example.sghss.bo.UnidadeBO;
import com.example.sghss.model.RelFinanceiro;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/financeiro")
public class RelFinanceiroController {

    @Autowired
    private RelFinanceiroBO bo;

    @Autowired
    private UnidadeBO unidadeBO;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("relatorio", new RelFinanceiro());
        model.addAttribute("unidades", unidadeBO.lista());
        return new ModelAndView("/financeiro/formulario", model);
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public String salva(@Valid @ModelAttribute RelFinanceiro relatorio,
        BindingResult result,
        RedirectAttributes attr) {

        if (result.hasErrors())
            return "financeiro/formulario";

        if (relatorio.getIdRelFinanceiro() == null) {
            bo.create(relatorio);
            attr.addFlashAttribute("feedback", "Relatório cadastrado com sucesso");
        } else {
            bo.update(relatorio);
            attr.addFlashAttribute("feedback", "Relatório atualizado com sucesso");
        }

        return "redirect:/financeiro";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("relatorios", bo.lista());
        return new ModelAndView("/financeiro/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable Long id, ModelMap model) {

        model.addAttribute("relatorio", bo.pesquisarPeloId(id));
        model.addAttribute("unidades", unidadeBO.lista());

        return new ModelAndView("/financeiro/formulario", model);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/financeiro";
    }
}
