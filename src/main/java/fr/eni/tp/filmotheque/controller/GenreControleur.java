package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bll.GenreService;
import fr.eni.tp.filmotheque.bo.Genre;
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
public class GenreControleur {

    @Autowired
    GenreService genreService;
    @Autowired
    FilmService filmService;


    @ModelAttribute("genres")
    public List<Genre> getListeGenres(){
        return genreService.consulterGenres();
    }

    @GetMapping("/ajouterGenre")
    public String afficherFormulaireCreationGenre(@RequestParam(required=false) Long edit, Model model, @AuthenticationPrincipal UtilisateurSpringSecurity user) {
        model.addAttribute("genre", new Genre());

        if (edit != null){
            model.addAttribute("genreEdit", genreService.consulterGenreParId(edit));
        }

        return "ajouterGenre";
    }

    @PostMapping("/ajouterGenre")
    public String ajouterUnGenre(@Valid Genre genre, BindingResult bindingResult, Model model, @AuthenticationPrincipal UtilisateurSpringSecurity user) {
        model.addAttribute("genre", genre);

        if (bindingResult.hasErrors()) {
            return "ajouterGenre";
        }
        genreService.creerGenre(genre);
        return "redirect:/ajouterGenre";
    }

    @GetMapping( "ajouterGenre/{id}/supprimer")
    public String supprimerUnGenre(@PathVariable Long id, Model model) {

        Genre genre = genreService.consulterGenreParId(id);
        model.addAttribute("message", "Êtes vous sur de vouloir supprimer le genre : " + genre.getTitre());
        model.addAttribute("action", "/ajouterGenre/" + id + "/supprimer");
        model.addAttribute("back", "/ajouterGenre");
        return "confirmation";
    }

    @PostMapping( "ajouterGenre/{id}/supprimer")
    public String supprimerUnGenre(@PathVariable Long id) {

        genreService.supprimerGenreParId(id);
        return "redirect:/ajouterGenre";
    }

}
