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
import com.example.sghss.bo.PrescricaoBO;
import com.example.sghss.bo.ProfissionalBO;
import com.example.sghss.model.Prescricao;
import com.example.sghss.model.TipoPrescricao;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/prescricoes")
public class PrescricaoController {

    @Autowired
    private PrescricaoBO bo;

    @Autowired
    private PacienteBO pacienteBO;

    @Autowired
    private ProfissionalBO profissionalBO;

    @ModelAttribute("tipoPrescricoes")
    public TipoPrescricao[] tipoPrescricoes() {
        return TipoPrescricao.values();
    }    

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("prescricao", new Prescricao());
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());
        return new ModelAndView("/prescricao/formulario", model);
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public String salva(@Valid @ModelAttribute Prescricao prescricao,
        BindingResult result,
        RedirectAttributes attr) {

        if (result.hasErrors())
            return "prescricao/formulario";
        
        prescricao.setPaciente(pacienteBO.pesquisarPeloId(prescricao.getPaciente().getId()));
        prescricao.setProfissional(profissionalBO.pesquisarPeloId(prescricao.getProfissional().getId()));

        if (prescricao.getId() == null) {
            bo.create(prescricao);
            attr.addFlashAttribute("feedback", "Prescrição cadastrada com sucesso");
        } else {
            bo.update(prescricao);
            attr.addFlashAttribute("feedback", "Prescrição atualizada com sucesso");
        }

        return "redirect:/prescricoes/lista";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("prescricoes", bo.lista());
        return new ModelAndView("prescricao/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable ("id") Long id, ModelMap model) {
        try{
            model.addAttribute("prescricao", bo.pesquisarPeloId(id));
            model.addAttribute("pacientes", pacienteBO.lista());
            model.addAttribute("profissionais", profissionalBO.lista());
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("/prescricao/formulario", model);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/prescricoes";
    }
}
