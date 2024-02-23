package fr.eni.tp.filmotheque.security;

import fr.eni.tp.filmotheque.bll.MembreService;
import fr.eni.tp.filmotheque.bo.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private MembreService membreService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Membre membre = membreService.recupererMembre(username);

        if (membre == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UtilisateurSpringSecurity(membre);
    }
}
