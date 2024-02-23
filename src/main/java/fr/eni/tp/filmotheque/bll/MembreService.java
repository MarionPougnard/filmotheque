package fr.eni.tp.filmotheque.bll;

import fr.eni.tp.filmotheque.bo.Membre;

import java.util.List;


public interface MembreService {
    public Membre recupererMembre(String pseudo);

    Membre consulterMembreParId(long id);
    List<Membre> consulterMembres();

    Membre creerMembre(Membre membre);

    void supprimerMembreParId(Long id);
}
