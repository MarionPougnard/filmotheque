package fr.eni.tp.filmotheque.api;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bll.GenreService;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.security.UtilisateurSpringSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/genre")
public class GenreRestControleur {

    @Autowired
    GenreService genreService;

    @GetMapping()
    public List<Genre> afficherGenres() {
       return genreService.consulterGenres();
    }


    /**
     * on laisse l'annotation valid, l'API nous retournera une erreur si le genre n'est pas valide
     * @param genre
     * @return
     */
    @PostMapping()
    public void ajouterUnGenre(@RequestBody @Valid Genre genre) {
        genreService.creerGenre(genre);
    }

    @PutMapping("/{id}")
    public void modifierGenre(@RequestBody  @Valid Genre genre, @PathVariable Long id) {
        genre.setId(id);
        genreService.putGenre(genre);
    }

    @DeleteMapping( "/{id}")
    public void supprimerUnGenre(@PathVariable Long id) {
        genreService.supprimerGenreParId(id);
    }


}
