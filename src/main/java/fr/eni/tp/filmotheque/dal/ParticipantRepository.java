package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

//    List<Participant> findByPrenomStartingWithOrNomStartingWith(String prenom, String nom);

//    @Query("select p from Personne p where p.prenom like :seach% or p.nom like :search%")
//    List<Participant> search(String search);
}
