package fr.eni.tp.filmotheque.controller;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bll.GenreService;
import fr.eni.tp.filmotheque.bll.MembreService;
import fr.eni.tp.filmotheque.bll.ParticipantService;
import fr.eni.tp.filmotheque.bo.*;
import fr.eni.tp.filmotheque.dal.FilmRepository;
import fr.eni.tp.filmotheque.dal.ParticipantRepository;
import fr.eni.tp.filmotheque.dto.ParametresRecherche;
import fr.eni.tp.filmotheque.security.UtilisateurSpringSecurity;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;


@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    FilmService filmService;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    MembreService membreService;
    @Autowired
    GenreService genreService;
    @Autowired
    ParticipantService participantService;




    @ModelAttribute("genres")
    public  List<Genre> getListeGenres(){
        return genreService.consulterGenres();
    }


    @ModelAttribute("participants")
    public  List<Participant> getListeParticipants(){
        return participantService.consulterParticipants();
    }

//    @GetMapping
//    public String rechercher(@RequestParam(required = false) String search,
//                             @RequestParam(required = false) String searchTitre,
//                             @RequestParam(required = false) String searchRealisateur,
//                             @RequestParam(required = false) String searchActeur,
//                             @RequestParam(required = false) String searchGenre,
//                             Film exampleFilm, Model model) {
//        model.addAttribute("film", new Film());
//        model.addAttribute("exampleFilm", new Film());
//
//        if (search != null && !search.isBlank()){
//            model.addAttribute("listeFilms", filmRepository.searchByAll(search));
////        } else if (exampleFilm != null) {
////            ExampleMatcher matcher = ExampleMatcher.matching()
////                    .withIgnoreNullValues()
////                    .withMatcher("titre", startsWith().ignoreCase());
////            model.addAttribute("exampleFilm", new Film());
////            model.addAttribute("listeFilms", filmRepository.findAll(Example.of(exampleFilm, matcher)));
//        } if (searchTitre != null && !searchTitre.isBlank()){
//            model.addAttribute("listeFilms", filmRepository.searchByTitre(searchTitre));
//        } if (searchRealisateur != null && !searchRealisateur.isBlank()){
//            model.addAttribute("listeFilms", filmRepository.searchByRealisateur(searchRealisateur));
//        } if (searchActeur != null && !searchActeur.isBlank()){
//            model.addAttribute("listeFilms", filmRepository.searchByActeur(searchActeur));
//        } if (searchGenre != null && !searchGenre.isBlank()){
//            model.addAttribute("listeFilms", filmRepository.searchByGenre(searchGenre));
//        } else {
//            model.addAttribute("listeFilms", filmService.consulterFilms());
//        }
//
//            return "films";
//    }
    @GetMapping
    public String rechercher(@RequestParam(required = false) String search, ParametresRecherche parametresRecherche, Model model) {
        if (search != null && !search.isBlank()) {
            model.addAttribute("listeFilms", filmService.rechercherFilms(search));
        } else if (parametresRecherche.isNotEmpty()){
            model.addAttribute("listeFilms", filmService.rechercherFilmsParFiltre(parametresRecherche));
        } else {
            model.addAttribute("listeFilms", filmService.consulterFilms());
        }
        model.addAttribute("parametresRecherche", parametresRecherche);

            return "films";
    }

    @GetMapping("/{filmId}")
    public String afficherUnFilm(@PathVariable("filmId") int id, Model model) {
        model.addAttribute("film", filmService.consulterFilmParId(id));
        model.addAttribute("isDisabled",true);
        model.addAttribute("isEmpty", false);
        return "filmDetail";
    }


    @PostMapping("/ajouterGenre")
    public String ajouterUnGenreViaPageAccueil(@Valid Genre genre, BindingResult bindingResult, Model model, @AuthenticationPrincipal UtilisateurSpringSecurity user) {
        model.addAttribute("genre", genre);
        genreService.creerGenre(genre);

        if (bindingResult.hasErrors()) {
            return "ajouterGenre";
        }
        return "redirect:/films";
    }


    @GetMapping("/ajouterFilm")
    public String afficherFormulaireCreationFilm(Film film, Model model, @AuthenticationPrincipal UtilisateurSpringSecurity user) {
        model.addAttribute("isDisabled",false);
        model.addAttribute("isEmpty", true);
        model.addAttribute("film", new Film());
        return "ajouterFilm";
    }

    @PostMapping("/ajouterFilm")
    public String ajouterUnFilm(@Valid Film film, BindingResult bindingResult,Model model,  @AuthenticationPrincipal UtilisateurSpringSecurity user) {
        model.addAttribute("isDisabled",false);
        model.addAttribute("isEmpty", true);
        model.addAttribute("film");
        filmService.creerFilm(film);

        if (bindingResult.hasErrors()) {
            return "ajouterFilm";
        }
        return "redirect:/films";
    }

}
