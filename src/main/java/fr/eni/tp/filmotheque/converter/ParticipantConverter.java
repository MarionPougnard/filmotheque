package fr.eni.tp.filmotheque.converter;

import fr.eni.tp.filmotheque.bll.FilmService;
import fr.eni.tp.filmotheque.bll.ParticipantService;
import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ParticipantConverter implements Converter<String, Participant> {
    @Autowired
    ParticipantService participantService;

    @Override
    public Participant convert(String participantFormatString) {
        return participantService.consulterParticipantParId(Long.parseLong(participantFormatString));
    }
}

