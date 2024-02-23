//package fr.eni.tp.filmotheque.bll.mock;
//
//import fr.eni.tp.filmotheque.bll.MembreService;
//import fr.eni.tp.filmotheque.bo.Membre;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class MembreServiceBouchon implements MembreService {
//    List<Membre> membres = new ArrayList<>();
//
//    public MembreServiceBouchon(PasswordEncoder passwordEncoder) {
//        Membre m1 = new Membre("ma", "po","membre1", passwordEncoder.encode("membre1"), false);
//        Membre m2 = new Membre("membre2", passwordEncoder.encode("membre2"), false);
//        Membre m3 = new Membre("admin", passwordEncoder.encode("admin"), true);
//        membres.add(m1);
//        membres.add(m2);
//        membres.add(m3);
//    }
//
//    @Override
//    public Membre recupererMembre(String pseudo) {
//        return null;
//    }
//
//    @Override
//    public List<Membre> consulterMembres() {
//        return null;
//    }
//
//    @Override
//    public Membre creerMembre(Membre membre) {
//        return null;
//    }
//
//    @Override
//    public void supprimerMembreParId(Long id) {
//
//    }
//
////    @Override
////    public Membre recupererMembre(String username) {
////        for (Membre membre : membres) {
////            if (membre.getPseudo().equals(username)) {
////                return membre;
////            }
////        }
////        throw new UsernameNotFoundException(username);
////    }
////
////    @Override
////    public List<Membre> consulterMembres() {
////        return null;
////    }
////
////    @Override
////    public Membre creerMembre(Membre membre) {
////
////        return membre;
////    }
////
////    @Override
////    public void supprimerMembreParId(Long id) {
////
////    }
//}
