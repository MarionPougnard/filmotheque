package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.MembreService;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.security.UtilisateurSpringSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MembreControleur {

    @Autowired
    MembreService membreService;


    @ModelAttribute("personnes")
    public List<Membre> getListeParticipants(){
        return membreService.consulterMembres();
    }

    @GetMapping("/ajouterPersonne/Membre")
    public String afficherFormulaireCreationMembre(@RequestParam(required=false) Long edit, Model model, @AuthenticationPrincipal UtilisateurSpringSecurity user) {
        model.addAttribute("isMembre",true);
        model.addAttribute("personne", new Membre());
        if (edit != null){
            model.addAttribute("personneEdit", membreService.consulterMembreParId(edit));
        }
        return "ajouterPersonne";
    }

    @PostMapping("/ajouterPersonne/Membre")
    public String ajouterUnMembre(@Valid Membre membre, BindingResult bindingResult, Model model, @AuthenticationPrincipal UtilisateurSpringSecurity user) {
        model.addAttribute("isMembre",true);
        model.addAttribute("personne");
        membreService.creerMembre(membre);

        if (bindingResult.hasErrors()) {
            return "ajouterPersonne";
        }
        return "redirect:/ajouterPersonne/Membre";
    }

    @GetMapping( "ajouterPersonne/Membre/{id}/supprimer")
    public String supprimerUnMembre(@PathVariable Long id, Model model) {

        Membre membre = membreService.consulterMembreParId(id);
        model.addAttribute("message", "Êtes vous sur de vouloir supprimer le membre : " + membre.getPseudo());
        model.addAttribute("action", "/ajouterPersonne/Membre" + id + "/supprimer");
        model.addAttribute("back", "/ajouterPersonne/Membre");
        return "confirmation";
    }

    @PostMapping( "ajouterPersonne/Membre/{id}/supprimer")
    public String supprimerUnMembre(@PathVariable Long id) {

        membreService.supprimerMembreParId(id);
        return "redirect:/ajouterPersonne/Membre";
    }
}

