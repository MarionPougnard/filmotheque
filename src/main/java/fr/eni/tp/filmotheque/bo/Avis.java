package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data // génère getter/setter/
@NoArgsConstructor
@AllArgsConstructor
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int note;
    private String commentaire;
    @ManyToOne
    private Membre membre;

    public Avis(int note, String commentaire, Membre membre) {
        this.note = note;
        this.commentaire = commentaire;
        this.membre = membre;
    }
}
