package bdbt_bada_project.SpringApplication;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        registry.addViewController("/koty").setViewName("koty");
        registry.addViewController("/psy").setViewName("psy");
        registry.addViewController("/inne").setViewName("inne");
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
    private ZwierzetaDAO zwierzeDao;
    @Autowired
    private RasaDAO rasaDao;
    @Autowired
    private GatunekDAO gatunekDao;

    @RequestMapping("/zwierzeta")
    public String showZwierzetaPage(Model model){
        List<Zwierze> listZwierze = zwierzeDao.list();
        List<Rasa> listRasa = rasaDao.list();


        model.addAttribute("listZwierze",listZwierze);
        model.addAttribute("list", listRasa);
        return "zwierzeta";
    }

    @RequestMapping("/koty")
    public String showKotyPage(Model model){
        List<Zwierze> listZwierze = zwierzeDao.list();
        List<Rasa> listRasa = rasaDao.list();


        model.addAttribute("listZwierze",listZwierze);
        model.addAttribute("list", listRasa);
        return "koty";
    }

    @RequestMapping("/psy")
    public String showPsyPage(Model model){
        List<Zwierze> listZwierze = zwierzeDao.list();
        List<Rasa> listRasa = rasaDao.list();


        model.addAttribute("listZwierze",listZwierze);
        model.addAttribute("list", listRasa);
        return "psy";
    }

    @RequestMapping("/inne")
    public String showInnePage(Model model){
        List<Zwierze> listZwierze = zwierzeDao.list();
        List<Rasa> listRasa = rasaDao.list();


        model.addAttribute("listZwierze",listZwierze);
        model.addAttribute("list", listRasa);
        return "inne";
    }
    @RequestMapping("/dodajzwierze")
    public String showDodajZwierzeForm(Model model){
        Zwierze zwierze = new Zwierze();
        List<Gatunek> listaGatunkow = gatunekDao.list();
        List<Rasa> listaRas = rasaDao.list();

        model.addAttribute("zwierze", zwierze);
        model.addAttribute("listaGatunkow", listaGatunkow);
        model.addAttribute("listaRas", listaGatunkow);
        return "dodajzwierze_form";
    }


    @RequestMapping(value ="/zapisz", method = RequestMethod.POST)
    public String save(@ModelAttribute("zwierze") Zwierze zwierze){
        zwierzeDao.save(zwierze);

        return "redirect:/zwierzeta";
    }

    @RequestMapping("/edytujzwierze/{nr_zwierzecia}")
    public ModelAndView showEditForm(@PathVariable(name="nr_zwierzecia") int nr_zwierzecia){
        ModelAndView mav = new ModelAndView("edit_form_zwierze");
        Zwierze zwierze = zwierzeDao.get(nr_zwierzecia);
        mav.addObject("zwierze", zwierze);
        return mav;
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String update(@ModelAttribute("zwierze") Zwierze zwierze){
        zwierzeDao.update(zwierze);
        return "redirect:/";
    }

    @RequestMapping("/usunzwierze/{nr_zwierzecia}")
    public String usunZwierze(@PathVariable(name = "nr_zwierzecia") Integer nr_zwierzecia){
        zwierzeDao.delete(nr_zwierzecia);
        return "redirect:/zwierzeta";
    }


//    private RasaDAO rasaDAO;
//    private GatunekDAO gatunekDAO;

}


