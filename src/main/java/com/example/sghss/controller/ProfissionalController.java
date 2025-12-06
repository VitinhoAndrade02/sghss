package   com.example.sghss.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.sghss.bo.ProfissionalBO;
import com.example.sghss.model.Profissional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalBO bo;

    @GetMapping("/novo")
    public ModelAndView novo(ModelMap model) {
        model.addAttribute("profissional", new Profissional());
        return new ModelAndView("profissional/formulario", model);
    }

    @PostMapping("/novo")
    public String salvar(@ModelAttribute Profissional profissional) {
        bo.create(profissional);
        return "redirect:/profissionais/lista";
    }

    @GetMapping("/lista")
    public ModelAndView lista(ModelMap model) {
        model.addAttribute("profissionais", bo.lista());
        return new ModelAndView("profissional/lista", model);
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id, ModelMap model) {
        model.addAttribute("profissional", bo.pesquisarPeloId(id));
        return new ModelAndView("profissional/formulario", model);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bo.delete(id);
        return "redirect:/profissionais/lista";
    }
}
