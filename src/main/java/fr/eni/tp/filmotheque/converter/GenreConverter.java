package fr.eni.tp.filmotheque.converter;


import fr.eni.tp.filmotheque.bll.GenreService;
import fr.eni.tp.filmotheque.bo.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenreConverter implements Converter<String, Genre> {
    @Autowired
    GenreService genreService;
    @Override
    public Genre convert(String genreFormatTexte) {
        return genreService.consulterGenreParId(Long.parseLong(genreFormatTexte));
    }
}
