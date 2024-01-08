package bdbt_bada_project.SpringApplication;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;


@Configuration
@Controller
public class AppController implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/main_admin").setViewName("admin/main_admin");
        registry.addViewController("/main_user").setViewName("user/main_user");
        registry.addViewController("/zwierzeta").setViewName("zwierzeta");
    }


    public class DashboardController
    {
        @RequestMapping("/main")
        public String defaultAfterLogin
                (HttpServletRequest request) {
            if
            (request.isUserInRole
                    ("ADMIN")) {
                return "redirect:/main_admin";
            }
            else if
            (request.isUserInRole
                            ("USER")) {
                return "redirect:/main_user";
            }
            else
            {
                return "redirect:/index";
            }
        }
    }

    @RequestMapping(value={"/main_admin"})
    public String showAdminPage(Model model) {
        return "admin/main_admin";
    }
    @RequestMapping(value={"/main_user"})
    public String showUserPage(Model model) {
        return "user/main_user";
    }

    @Autowired
    private ZwierzetaDAO dao;

    @RequestMapping("/zwierzeta")
    public String showZwierzetaPage(Model model){
        List<Zwierze> listZwierze = dao.list();
        model.addAttribute("listZwierze",listZwierze);
        return "zwierzeta";
    }
    @RequestMapping("/dodajzwierze")
    public String showDodajZwierzeForm(Model model){
        Zwierze zwierze = new Zwierze();
        model.addAttribute("zwierze",zwierze);
        return "dodajzwierze_form";
    }

    @RequestMapping(value ="/zapisz", method = RequestMethod.POST)
    public String save(@ModelAttribute("zwierze") Zwierze zwierze){
        dao.save(zwierze);

        return "redirect:/zwierzeta";
    }

    @RequestMapping("/edytuj/{nr_zwierzecia}")
    public ModelAndView showEditForm(@PathVariable(name="nr_zwierzecia") int nr_zwierzecia){
        ModelAndView mav = new ModelAndView("edit_form_zwierze");
        Zwierze zwierze = dao.get(nr_zwierzecia);
        mav.addObject("zwierze", zwierze);
        return mav;
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String update(@ModelAttribute("zwierze") Zwierze zwierze){
        dao.update(zwierze);
        return "redirect:/";
    }

//    private RasaDAO rasaDAO;
//    private GatunekDAO gatunekDAO;

}


