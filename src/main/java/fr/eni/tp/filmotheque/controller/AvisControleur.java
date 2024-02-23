package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bll.MembreService;
import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.security.UtilisateurSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class AvisControleur {
    @Autowired
    FilmService filmService;
    @Autowired
    MembreService membreService;


    @GetMapping("/films/{filmId}/avis")
    public String afficherFormulaireAvis(@PathVariable("filmId") int id, Model model) {
        model.addAttribute("film", filmService.consulterFilmParId(id));
        model.addAttribute("avis", new Avis());
        return "avis";
    }

    @PostMapping("/films/{filmId}/avis")
    public String ajouterUnAvis(@PathVariable("filmId") int id, Avis avis, Model model, @AuthenticationPrincipal UtilisateurSpringSecurity user) {
        model.addAttribute("film", filmService.consulterFilmParId(id));
        Membre membre = membreService.recupererMembre(user.getUsername());
        avis.setMembre(membre);
        filmService.publierAvis(avis, id);
        return "redirect:/films/{filmId}";
    }
}
