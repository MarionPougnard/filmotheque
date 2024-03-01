package fr.eni.tp.filmotheque.bll.jpa;

import fr.eni.tp.filmotheque.bll.MembreService;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.bo.Participant;
import fr.eni.tp.filmotheque.dal.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MembreServiceImpl implements MembreService {

    @Autowired
    private MembreRepository membreRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Membre recupererMembre(String pseudo) {

        return membreRepository.findByPseudo(pseudo);
    }

    @Override
    public Membre consulterMembreParId(long id) {
        return membreRepository.findById(id).get();
    }

    @Override
    public List<Membre> consulterMembres() {
        return membreRepository.findAll();
    }

    @Override
    public Membre creerMembre(Membre membre) {
        Membre membreAVecLeMemePseudo = recupererMembre(membre.getPseudo());
        if (membreAVecLeMemePseudo != null && membreAVecLeMemePseudo.getId() != membre.getId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Un membre avec ce pseudo existe déjà");
        }

        membre.setMotDePasse(passwordEncoder.encode(membre.getMotDePasse()));
        return membreRepository.save(membre);
    }

    @Override
    public void supprimerMembreParId(Long id) {
        if (membreRepository.existsById(id)){
            membreRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le membre n'a pas été trouvé");
        }
    }

    @Override
    public void putMembre(Membre membre) {
        if (membreRepository.existsById(membre.getId())) {
            membreRepository.save(membre);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le membre n'a pas été trouvé");
        }
    }
}
