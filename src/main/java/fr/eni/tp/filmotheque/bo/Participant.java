package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Participant extends Personne {

    public Participant(String nom, String prenom) {
        super(nom, prenom);
    }
    public Participant(long id, String nom, String prenom) {
        super(id, nom, prenom);
    }


}
