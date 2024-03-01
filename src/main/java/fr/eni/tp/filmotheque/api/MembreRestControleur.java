package fr.eni.tp.filmotheque.api;

import fr.eni.tp.filmotheque.bll.MembreService;
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

@RestController
@CrossOrigin
@RequestMapping("/api/membre")
public class MembreRestControleur {

    @Autowired
    MembreService membreService;

    @GetMapping()
    public List<Membre> afficherMembres() {
        return membreService.consulterMembres();
    }

    @PostMapping()
    public void ajouterUnMembre(@RequestBody @Valid Membre membre) {
       membreService.creerMembre(membre);
    }

    @PutMapping( "/{id}")
    public void modifierUnMembre(@RequestBody @Valid Membre membre, @PathVariable Long id) {
        membre.setId(id);
        membreService.putMembre(membre);
    }

    @DeleteMapping( "/{id}")
    public void supprimerUnMembre(@PathVariable Long id) {
        membreService.supprimerMembreParId(id);
    }
}

