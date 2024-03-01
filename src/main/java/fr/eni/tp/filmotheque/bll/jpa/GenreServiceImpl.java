package fr.eni.tp.filmotheque.bll.jpa;

import fr.eni.tp.filmotheque.bll.GenreService;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.dal.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Profile("prod")
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> consulterGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre consulterGenreParId(long id) {
        return genreRepository.findById(id).get();
    }

    @Override
    public void creerGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void supprimerGenreParId(Long id) {
        if (genreRepository.existsById(id)){
            genreRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le genre n'a pas été trouvé");
        }

    }

    @Override
    public void putGenre(Genre genre) {
        if (genreRepository.existsById(genre.getId())){
            genreRepository.save(genre);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le genre n'a pas été trouvé");
        }
    }

}
