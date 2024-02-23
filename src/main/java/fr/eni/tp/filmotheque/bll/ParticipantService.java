package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.bo.Participant;

import java.util.List;

public interface ParticipantService {

    List<Participant> consulterParticipants();

    Participant consulterParticipantParId(long id);

    void creerParticipant(Participant participant);

    void supprimerParticipantParId(Long id);
}
