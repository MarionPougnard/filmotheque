package fr.eni.tp.filmotheque.api;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bll.GenreService;
import fr.eni.tp.filmotheque.bll.MembreService;
import fr.eni.tp.filmotheque.bll.ParticipantService;
import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.FilmRepository;
import fr.eni.tp.filmotheque.dto.ParametresRecherche;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/films")
public class FilmRestController {

    @Autowired
    FilmService filmService;
    @Autowired
    MembreService membreService;
    @Autowired
    GenreService genreService;
    @Autowired
    ParticipantService participantService;

    @GetMapping
    public List<Film> rechercher(@RequestParam(required = false) String search, ParametresRecherche parametresRecherche) {
        if (search != null && !search.isBlank()) {
            return filmService.rechercherFilms(search);
        } else if (parametresRecherche.isNotEmpty()){
            return filmService.rechercherFilmsParFiltre(parametresRecherche);
        } else {
            return filmService.consulterFilms();
        }
    }

    @GetMapping("/{filmId}")
    public Film afficherUnFilm(@PathVariable("filmId") long id) {
        return filmService.consulterFilmParId(id);
    }

    @PostMapping("/ajouterFilm")
    public void ajouterUnFilm(@RequestBody @Valid Film film) {
        filmService.creerFilm(film);
    }

    @PutMapping("/{filmId}")
    public void modifierFilms(@RequestBody @Valid Film film, @PathVariable("filmId") Long id) {
        film.setId(id);
        filmService.putFilm(film);
    }

    @DeleteMapping("/{filmId}")
    public void supprimerFilm(@PathVariable("filmId") long id){
        filmService.deleteFilm(id);
    }

    @PostMapping("/{filmId}/avis")
    public void ajouterUnAvis(@RequestBody @Valid Avis avis, @PathVariable("filmId") long id) {
        avis.setMembre(membreService.consulterMembres().get(0));
        filmService.publierAvis(avis, id);
    }

}
