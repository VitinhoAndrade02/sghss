package com.example.sghss.controller;
//Ajusta o Prontuario
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
import com.example.sghss.bo.ProntuarioBO;
import com.example.sghss.model.Prontuario;


@Controller
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioBO bo;
    
    @Autowired
    private PacienteBO pacienteBO;
    

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        Long pacienteId= null;
        model.addAttribute("pacienteId", pacienteId);
        model.addAttribute("prontuario", new Prontuario());
        model.addAttribute("pacientes", pacienteBO.lista());
        return new ModelAndView("prontuario/formulario", model);
    }
    //Daqui para baixo pode ser que apague

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public String salva(@ModelAttribute Prontuario prontuario,
        BindingResult result,
        RedirectAttributes attr) {
        if (result.hasErrors()) 
            return "prontuario/formulario";
        

        if (prontuario.getId() == null) {
            bo.create(prontuario);
            attr.addFlashAttribute("feedback", "Prontuario foi cadastrado com sucesso");
        } 

        else {
            bo.update(prontuario);
            attr.addFlashAttribute("feedback", "Prontuario foi atualizado com sucesso");

        }
        return "redirect:/prontuarios/lista";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("prontuarios", bo.lista());
        return new ModelAndView("/prontuario/lista", model);
    }

    @RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
    public ModelAndView edita(@PathVariable ("id") Long id, ModelMap model) {
        try{
            model.addAttribute("prontuario", bo.pesquisarPeloId(id));
            model.addAttribute("pacientes", pacienteBO.lista());
        }catch(Exception e){
             e.printStackTrace();
            } 
        return new ModelAndView("prontuario/formulario", model);
        
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/prontuarios/lista";
    }
}
