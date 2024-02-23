package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Avis;
import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.GenreRepository;
import fr.eni.tp.filmotheque.dto.ParametresRecherche;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilmService {

  List<Film> consulterFilms();

  Film consulterFilmParId(long id);

  void creerFilm(Film film);

 void publierAvis(Avis avis, long idFilm);

 List<Film> rechercherFilms(String search);
 List<Film> rechercherFilmsParFiltre(ParametresRecherche parametresRecherche);

  List<Avis> consulterAvis(long idFilm);
}
