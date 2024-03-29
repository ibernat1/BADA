package bdbt_bada_project.SpringApplication;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;
import java.util.Objects;


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
        registry.addViewController("/adopcja").setViewName("user/adopcja");
        registry.addViewController("/zwierzeta_admin").setViewName("admin/zwierzeta_admin");
    }


    public class DashboardController
    {
        @RequestMapping("/main")
        public String defaultAfterLogin
                (HttpServletRequest request) {
            if
            (request.isUserInRole
                    ("ROLE_ADMIN")) {
                return "redirect:/main_admin";
            }
            else if
            (request.isUserInRole
                            ("ROLE_USER")) {
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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate(); // Wylogowanie poprzez zniszczenie sesji
        return "redirect:/"; // Przekierowanie na stronę główną lub inną stronę po wylogowaniu
    }


    @RequestMapping("/zwierzeta")
    public String showZwierzetaPage(Model model){

        return "zwierzeta";
    }

    @Autowired
    private ZwierzetaDAO zwierzeDao;
    @Autowired
    private RasaDAO rasaDao;
    @Autowired
    private GatunekDAO gatunekDao;

    @RequestMapping("/wszystkie")
    public String showWszystkiePage(@RequestParam(name = "gatunek", required = false) String gatunek, Model model){
        List<Zwierze> listZwierze = zwierzeDao.list();
        List<Rasa> listRasa = rasaDao.list();
        List<Zwierze> filteredZwierzeta;

        model.addAttribute("listZwierze",listZwierze);
        model.addAttribute("list", listRasa);


        if("wszystkie".equals(gatunek) || gatunek == null){
            filteredZwierzeta = zwierzeDao.list();
        }
        else {
            if (gatunek != null && !gatunek.isEmpty()) {
                filteredZwierzeta = zwierzeDao.findByGatunek(gatunek);
            }
            else{
                filteredZwierzeta = zwierzeDao.list();
            }
        }

        model.addAttribute("listZwierze", filteredZwierzeta);

        return "wszystkie";
    }

    @RequestMapping("/zwierzeta_admin")
    public String showZwierzetaAdminPage(@RequestParam(name = "gatunek", required = false) String gatunek, Model model){
        List<Zwierze> listZwierze = zwierzeDao.list();
        List<Rasa> listRasa = rasaDao.list();
        List<Zwierze> filteredZwierzeta;

        model.addAttribute("listZwierze",listZwierze);
        model.addAttribute("list", listRasa);


        if("wszystkie".equals(gatunek) || gatunek == null){
            filteredZwierzeta = zwierzeDao.list();
        }
        else {
            if (gatunek != null && !gatunek.isEmpty()) {
                filteredZwierzeta = zwierzeDao.findByGatunek(gatunek);
            }
            else{
                filteredZwierzeta = zwierzeDao.list();
            }
        }

        model.addAttribute("listZwierze", filteredZwierzeta);

        return "admin/zwierzeta_admin";
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
        model.addAttribute("listaRas", listaRas);
        return "dodajzwierze_form";
    }


    @RequestMapping(value ="/zapisz", method = RequestMethod.POST)
    public String save(@ModelAttribute("zwierze") Zwierze zwierze){
        zwierzeDao.save(zwierze);

        return "redirect:/zwierzeta_admin";
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
        System.out.println(zwierze.getNr_zwierzecia());
        zwierzeDao.update(zwierze);
        return "redirect:/zwierzeta_admin";
    }

    @RequestMapping("/usunzwierze/{nr_zwierzecia}")
    public String usunZwierze(@PathVariable(name = "nr_zwierzecia") Integer nr_zwierzecia){
        zwierzeDao.delete(nr_zwierzecia);
        return "redirect:/zwierzeta_admin";
    }

    @RequestMapping("/adopcja")
    public String showAdopcjaPage(@RequestParam(name = "gatunek", required = false) String gatunek, Model model){
        List<Zwierze> listZwierze = zwierzeDao.list();
        List<Rasa> listRasa = rasaDao.list();
        List<Zwierze> filteredZwierzeta;

        model.addAttribute("listZwierze",listZwierze);
        model.addAttribute("list", listRasa);


        if("wszystkie".equals(gatunek) || gatunek == null){
            filteredZwierzeta = zwierzeDao.list();
        }
        else {
            if (gatunek != null && !gatunek.isEmpty()) {
                filteredZwierzeta = zwierzeDao.findByGatunek(gatunek);
            }
            else{
                filteredZwierzeta = zwierzeDao.list();
            }
        }

        model.addAttribute("listZwierze", filteredZwierzeta);

        return "user/adopcja";
    }

    @RequestMapping("/adoptujzwierze/{nr_zwierzecia}")
    public String adoptujZwierze(@PathVariable(name = "nr_zwierzecia") Integer nr){
        Zwierze zwierze=zwierzeDao.get(nr);
        zwierzeDao.adoptujZwierze(zwierze,1);
        return "redirect:/zwierzeta";
    }

    @RequestMapping("/adoptuj_zwierze/{nr_zwierzecia}")
    public String adoptuj_Zwierze(@PathVariable(name="nr_zwierzecia") int nr){
        Zwierze zwierze=zwierzeDao.get(nr);
        int user_id=1; //na razie niech będzie na stałe, potem moze to jakoś przekminimy
        zwierzeDao.adoptujZwierze(zwierze, user_id);
        return "redirect:/zwierzeta";
    }


//    private RasaDAO rasaDAO;
//    private GatunekDAO gatunekDAO;

}


