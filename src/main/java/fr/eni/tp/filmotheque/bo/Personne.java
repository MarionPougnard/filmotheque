package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data // génère getter/setter/
@NoArgsConstructor
@AllArgsConstructor
public abstract class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
}
