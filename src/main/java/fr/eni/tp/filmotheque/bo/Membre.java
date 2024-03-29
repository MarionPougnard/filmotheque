package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data // génère getter/setter/constructeur sans argument/to string
@NoArgsConstructor
@AllArgsConstructor
public class Membre extends Personne {
    @NotNull(message = "le pseudo est obligatoire")
    private String pseudo;
    @NotNull(message = "le mot de passe est obligatoire")
    @ToString.Exclude
    private String motDePasse;
    private boolean admin = false;

    public Membre(String nom, String prenom, String pseudo, String motDePasse) {
        super(nom, prenom);
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
    }

    public Membre(String nom, String prenom, String pseudo, String motDePasse, boolean admin) {
        super(nom, prenom);
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.admin = admin;
    }

}
