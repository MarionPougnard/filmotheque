package fr.eni.tp.filmotheque.api;

import fr.eni.tp.filmotheque.bll.ParticipantService;
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

@RestController
@CrossOrigin
@RequestMapping("/api/participant")
public class ParticipantRestControleur {

    @Autowired
    ParticipantService participantService;

    @GetMapping()
    public List<Participant> afficherParticipants() {
        return participantService.consulterParticipants();
    }

    @PostMapping()
    public void ajouterUnParticipant(@RequestBody @Valid Participant participant) {
        participantService.creerParticipant(participant);
    }

    @PutMapping( "/{id}")
    public void modifierUnParticipant(@RequestBody @Valid Participant participant, @PathVariable Long id) {
        participant.setId(id);
        participantService.putParticipant(participant);
    }

    @DeleteMapping( "/{id}")
    public void supprimerUnParticipant(@PathVariable Long id) {
        participantService.supprimerParticipantParId(id);
    }
}
