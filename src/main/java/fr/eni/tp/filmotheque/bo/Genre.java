package fr.eni.tp.filmotheque.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data // génère getter/setter/
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    @GeneratedValue
    private long id;
    private String titre;

}
