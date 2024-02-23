package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
