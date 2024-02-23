package fr.eni.tp.filmotheque.converter;

import fr.eni.tp.filmotheque.bll.MembreService;
import fr.eni.tp.filmotheque.bo.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MembreConverter implements Converter<String, Membre> {

   @Autowired
    MembreService membreService;

   @Override
    public Membre convert(String membreFormatString) {
       return membreService.recupererMembre(membreFormatString);
   }
}
