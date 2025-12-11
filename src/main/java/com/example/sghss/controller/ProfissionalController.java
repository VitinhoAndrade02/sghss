package   com.example.sghss.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.sghss.bo.ProfissionalBO;
import com.example.sghss.model.Profissional;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalBO bo;

    @RequestMapping(value = "/novo", method = RequestMethod.GET)
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("profissional", new Profissional());
        return new ModelAndView("profissional/formulario", model);
    }

    @RequestMapping(value = "/novo", method = RequestMethod.POST)
    public String salva(@ModelAttribute Profissional profissional) {
        bo.create(profissional);
        return "redirect:/profissionais";
    }

    @RequestMapping(value= "/lista", method= RequestMethod.GET)
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("profissionais", bo.lista());
        return new ModelAndView("profissional", model);
    }

    @RequestMapping(value= "/editar/{id}", method= RequestMethod.GET)
    public ModelAndView editar(@PathVariable Long id, ModelMap model) {
        model.addAttribute("profissional", bo.pesquisarPeloId(id));
        return new ModelAndView("profissional/formulario", model);
    }

    @RequestMapping(value= "/delete/{id}", method= RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/profissionais";
    }
}
