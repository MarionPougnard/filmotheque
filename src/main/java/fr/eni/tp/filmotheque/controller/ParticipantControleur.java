package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.ParticipantService;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.bo.Participant;
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
public class ParticipantControleur {


    @Autowired
    ParticipantService participantService;

    @ModelAttribute("personnes")
    public List<Participant> getListeParticipants(){
        return participantService.consulterParticipants();
    }


    @GetMapping("/ajouterPersonne/Participant")
    public String afficherFormulaireCreationParticipant(@RequestParam(required=false) Long edit, Model model, @AuthenticationPrincipal UtilisateurSpringSecurity user) {
        model.addAttribute("isMembre",false);
        model.addAttribute("personne", new Participant());
        if (edit != null){
            model.addAttribute("personneEdit", participantService.consulterParticipantParId(edit));
        }
        return "ajouterPersonne";
    }

    @PostMapping("/ajouterPersonne/Participant")
    public String ajouterUnParticipantViaPageAccueil(@Valid Participant participant, BindingResult bindingResult, Model model, @AuthenticationPrincipal UtilisateurSpringSecurity user) {
        model.addAttribute("isMembre",false);
        model.addAttribute("personne");
        participantService.creerParticipant(participant);

        if (bindingResult.hasErrors()) {
            return "ajouterPersonne/Participant";
        }
        return "redirect:/ajouterPersonne/Participant";
    }

    @GetMapping( "ajouterPersonne/Participant/{id}/supprimer")
    public String supprimerUnParticipant(@PathVariable Long id, Model model) {

        Participant participant = participantService.consulterParticipantParId(id);
        model.addAttribute("message", "Êtes vous sur de vouloir supprimer le participant : " + participant.getPrenom() + " " +participant.getNom());
        model.addAttribute("action", "/ajouterPersonne/Participant" + id + "/supprimer");
        model.addAttribute("back", "/ajouterPersonne/Participant");
        return "confirmation";
    }

    @PostMapping( "ajouterPersonne/Participant/{id}/supprimer")
    public String supprimerUnParticipant(@PathVariable Long id) {

        participantService.supprimerParticipantParId(id);
        return "redirect:/ajouterPersonne/Participant";
    }
}
