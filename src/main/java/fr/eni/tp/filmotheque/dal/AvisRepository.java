package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisRepository extends JpaRepository<Avis, Long> {
}
