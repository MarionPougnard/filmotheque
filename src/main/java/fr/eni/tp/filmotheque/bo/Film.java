package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data // génère getter/setter/
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank @Size(max=250)
    private String titre;
    @NotNull @Min(1900)
    private int annee;
    @Min(1)
    private int duree;
    @Size(min = 20, max = 250)
    private String synopsis;

    @NotNull(message = "un film a toujours un réalisateur")
    @ManyToOne
    private Participant realisateur;
    @ManyToMany
    private List<Participant> acteurs = new ArrayList<>();
    @NotNull(message = "le genre est obligatoire")
    @ManyToOne
    private Genre genre;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "film_id")
    private List<Avis> avis = new ArrayList<>();

    public Film(int id, String titre, int annee, int duree, String synopsis) {
        this.id = id;
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.synopsis = synopsis;
    }

    public Film(int id, String titre, int annee, int duree, String synopsis, Participant realisateur, List<Participant> acteurs, Genre genre, List<Avis> avis)  {
        this.id = id;
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.synopsis = synopsis;
        this.realisateur = realisateur;
        this.acteurs = acteurs;
        this.genre = genre;
        this.avis = avis;
    }


}
