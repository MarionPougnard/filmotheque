package fr.eni.tp.filmotheque.bll.jpa;

import fr.eni.tp.filmotheque.bll.ParticipantService;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        participantRepository.deleteById(id);
    }
}
