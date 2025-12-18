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
import com.example.sghss.model.Profissional;
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

    @RequestMapping(value = "/novo/{profissionalId}", method = RequestMethod.GET)
    public ModelAndView novo(@PathVariable Long profissionalId, ModelMap model) {

        Profissional profissional = profissionalBO.pesquisarPeloId(profissionalId);

        Prescricao prescricao = new Prescricao();
        prescricao.setProfissional(profissional);

        model.addAttribute("prescricao", prescricao);
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());
        return new ModelAndView("/prescricao/formulario", model);
    }

    @RequestMapping(value = "/novo/{profissionalId}", method = RequestMethod.POST)
    public String salva(@PathVariable Long profissionalId, @Valid @ModelAttribute Prescricao prescricao,
        BindingResult result,
        RedirectAttributes attr) {

        if (result.hasErrors())
            return "prescricao/formulario";
        
        prescricao.setPaciente(pacienteBO.pesquisarPeloId(prescricao.getPaciente().getId()));
        prescricao.setProfissional(profissionalBO.pesquisarPeloId(prescricao.getProfissional().getId()));

        Profissional profissional = profissionalBO.pesquisarPeloId(profissionalId);
        prescricao.setProfissional(profissional);

        bo.create(prescricao);
        attr.addFlashAttribute("feedback", "Prescrição cadastrado com sucesso");
        return "redirect:/profissionais/" + profissionalId + "/prescricoes";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("prescricoes", bo.lista());
        return new ModelAndView("prescricao/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable ("id") Long id, ModelMap model) {
        Prescricao prescricao = bo.pesquisarPeloId(id);
        model.addAttribute("prescricao", bo.pesquisarPeloId(id));
        model.addAttribute("profissional", prescricao.getProfissional()); 
        model.addAttribute("pacientes", pacienteBO.lista());
        model.addAttribute("profissionais", profissionalBO.lista());

        return new ModelAndView("/prescricao/formulario", model);
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvar(@ModelAttribute Prescricao prescricao) {
        Prescricao prescricaoBanco = bo.pesquisarPeloId(prescricao.getId());
        prescricao.setProfissional(prescricaoBanco.getProfissional()); 
        bo.update(prescricao); 
        return "redirect:/profissionais/" + prescricao.getProfissional().getId() + "/prescricoes";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable ("id") Long id) {

        Prescricao prescricao = bo.pesquisarPeloId(id);
        Long profissionalId = prescricao.getProfissional().getId();

        bo.delete(id);
        
        return "redirect:/prescricoes" + profissionalId + "/prescricoes";
    }
}
