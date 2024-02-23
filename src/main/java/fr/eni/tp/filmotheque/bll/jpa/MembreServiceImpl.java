package fr.eni.tp.filmotheque.bll.jpa;

import fr.eni.tp.filmotheque.bll.MembreService;
import fr.eni.tp.filmotheque.bo.Membre;
import fr.eni.tp.filmotheque.dal.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        membre.setMotDePasse(passwordEncoder.encode(membre.getMotDePasse()));
        return membreRepository.save(membre);
    }

    @Override
    public void supprimerMembreParId(Long id) {
        membreRepository.deleteById(id);
    }
}
