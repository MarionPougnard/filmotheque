package fr.eni.tp.filmotheque.bll.jpa;

import fr.eni.tp.filmotheque.bll.ParticipantService;
import fr.eni.tp.filmotheque.bo.Genre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.ParticipantRepository;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;


    @Override
    public List<Participant> consulterParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public Participant consulterParticipantParId(long id) {
        return participantRepository.findById(id).get();
    }

    @Override
    public void creerParticipant(Participant participant) {
        participantRepository.save(participant);
    }

    @Override
    public void supprimerParticipantParId(Long id) {
        if (participantRepository.existsById(id)){
            participantRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le participant n'a pas été trouvé");
        }
    }

    @Override
    public void putParticipant(Participant participant) {
        if (participantRepository.existsById(participant.getId())){
            participantRepository.save(participant);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le participant n'a pas été trouvé");
        }
    }
}
