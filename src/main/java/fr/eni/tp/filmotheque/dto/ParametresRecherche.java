package fr.eni.tp.filmotheque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ParametresRecherche {
    private String search;
    private Long idGenre;
    private Long idRealisateur;
    private Long idActeur;

    public boolean isNotEmpty(){
        return (search != null) ||
                (idGenre != null) ||
                (idRealisateur != null) ||
                (idActeur != null);
    }

}
