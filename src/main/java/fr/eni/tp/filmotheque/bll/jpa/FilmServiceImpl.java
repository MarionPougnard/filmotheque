package fr.eni.tp.filmotheque.bll.jpa;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bo.*;
import fr.eni.tp.filmotheque.dal.AvisRepository;
import fr.eni.tp.filmotheque.dal.FilmRepository;
import fr.eni.tp.filmotheque.dto.ParametresRecherche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Profile("prod")
public class FilmServiceImpl implements FilmService{
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private AvisRepository avisRepository;

    @Override
    public List<Film> consulterFilms() {
        return filmRepository.findAll();
    }

    /**
     * @return film si id correspond
     * @return null si inconnu
     */
    @Override
    public Film consulterFilmParId(long id) {
        return filmRepository.findById(id).get();
    }


    @Override
    public void creerFilm(Film film) {
        filmRepository.save(film);
    }

    @Override
    public void deleteFilm(long id) {
        if (filmRepository.findById(id).isPresent()){
            filmRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "le film n'existe pas");
        }
    }

    @Override
    public void putFilm(Film film) {
        if (filmRepository.existsById(film.getId())){
            filmRepository.save(film);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "le film n'existe pas");
        }
    }

    @Override
    public void publierAvis(Avis avis, long idFilm) {
        avisRepository.save(avis);
        Film filmSelectionne = consulterFilmParId(idFilm);
        if (filmSelectionne != null) {
            filmSelectionne.getAvis().add(avis);
            filmRepository.save(filmSelectionne);
        }
    }

    @Override
    public List<Film> rechercherFilms(String search) {
        return filmRepository.searchByAll(search);
    }

    @Override
    public List<Film> rechercherFilmsParFiltre(ParametresRecherche parametresRecherche) {
        return filmRepository.searchByFiltre(parametresRecherche);
    }


    @Override
    public List<Avis> consulterAvis(long idFilm) {
        Film filmSelectionne = consulterFilmParId(idFilm);
        return filmSelectionne.getAvis();
    }
}
