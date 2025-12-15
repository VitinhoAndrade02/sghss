package   com.example.sghss.controller;
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

import com.example.sghss.bo.ProfissionalBO;
import com.example.sghss.model.Especialidade;
import com.example.sghss.model.Profissional;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalBO bo;

    @ModelAttribute("especialidades")
    public Especialidade[] especialidades() {
        return Especialidade.values();
    }

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("profissional", new Profissional());
        return new ModelAndView("/profissional/formulario", model);
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public String salva(@Valid @ModelAttribute Profissional profissional,
        BindingResult result,
        RedirectAttributes attr) {
        if (result.hasErrors()) 
            return "profissional/formulario";
        

        if (profissional.getId() == null) {
            bo.create(profissional);
            attr.addFlashAttribute("feedback", "Profissional foi cadastrado com sucesso");
        } 

        else {
            bo.update(profissional);
            attr.addFlashAttribute("feedback", "Profissional foi atualizado com sucesso");

        }
        return "redirect:/profissionais"; 
    }

    @RequestMapping(value= "", method= RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("profissionais", bo.lista());
        return new ModelAndView("/profissional/lista", model);
    }

    @RequestMapping(value= "/edita/{id}", method= RequestMethod.GET)
    public ModelAndView edita(@PathVariable ("id") Long id, ModelMap model) {
        try{
            model.addAttribute("profissional", bo.pesquisarPeloId(id));
        }catch(Exception e) {
            e.printStackTrace();
        }       
        return new ModelAndView("/profissional/formulario", model);
    }

    @RequestMapping(value= "/delete/{id}", method= RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/profissionais";
    }
}
