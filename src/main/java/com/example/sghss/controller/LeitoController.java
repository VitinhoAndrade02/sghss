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

import com.example.sghss.bo.LeitoBO;
import com.example.sghss.bo.UnidadeBO;
import com.example.sghss.model.Leito;
import com.example.sghss.model.StatusLeito;
import com.example.sghss.model.TipoLeito;
import com.example.sghss.model.Unidade;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/leitos")
public class LeitoController {

    @Autowired
    private LeitoBO bo;

    @Autowired
    private UnidadeBO unidadeBO;

    // GET para criar leito de uma unidade específica
    @RequestMapping(value = "/novo/{unidadeId}", method = RequestMethod.GET)
    public ModelAndView novo(@PathVariable Long unidadeId, ModelMap model) {

        Unidade unidade = unidadeBO.pesquisarPeloId(unidadeId);

        Leito leito = new Leito();
        leito.setUnidade(unidade);

        model.addAttribute("leito", leito);
        model.addAttribute("unidade", unidade);
        model.addAttribute("statusLeito", StatusLeito.values());
        model.addAttribute("tiposLeito", TipoLeito.values());
        return new ModelAndView("/leito/formulario", model);
    }

    // POST para salvar leito de uma unidade específica
    @RequestMapping(value = "/novo/{unidadeId}", method = RequestMethod.POST)
    public String salva(@PathVariable Long unidadeId, @Valid @ModelAttribute Leito leito,
                        BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) return "leito/formulario";

        Unidade unidade = unidadeBO.pesquisarPeloId(unidadeId);
        leito.setUnidade(unidade);

        bo.create(leito);
        attr.addFlashAttribute("feedback", "Leito cadastrado com sucesso");
        return "redirect:/unidades/" + unidadeId + "/leitos";
    }

   
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("leitos", bo.lista());
        return new ModelAndView("/leito/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable Long id, ModelMap model) {
         Leito leito = bo.pesquisarPeloId(id);
        model.addAttribute("leito", bo.pesquisarPeloId(id));
        model.addAttribute("unidade", leito.getUnidade()); // 🔥 ESSENCIAL
        model.addAttribute("statusLeito", StatusLeito.values());
        model.addAttribute("tiposLeito", TipoLeito.values());
        return new ModelAndView("/leito/formulario", model);
    }

   @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {

    Leito leito = bo.pesquisarPeloId(id);
    Long unidadeId = leito.getUnidade().getId();

    bo.delete(id);

    return "redirect:/unidades/" + unidadeId + "/leitos";
}

}
