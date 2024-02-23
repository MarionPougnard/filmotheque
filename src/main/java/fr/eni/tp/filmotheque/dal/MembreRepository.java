package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembreRepository extends JpaRepository<Membre, Long> {

    Membre findByPseudo(String pseudo);
}
