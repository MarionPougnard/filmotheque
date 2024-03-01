package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> consulterGenres();

    Genre consulterGenreParId(long id);

    void creerGenre(Genre genre);

    void supprimerGenreParId(Long id);

    void putGenre(Genre genre);

}
