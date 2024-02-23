package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Film;
import fr.eni.tp.filmotheque.dto.ParametresRecherche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("select f from Film f where f.titre like :search%")
    List<Film> searchByTitre(String search);

    @Query(value = "select distinct f.id, f.titre, genre_id, g.titre as titre_genre " +
            "from Film f " +
            // jointure avec les réalisateurs
            "inner join genre g on f.genre_id = g.id " +

            // jointure avec le titre
            "where g.titre like :searchGenre% ", nativeQuery = true)
    List<Film> searchByGenre(String searchGenre);

    @Query(value = "select distinct f.id, annee, duree, synopsis, f.titre, genre_id, realisateur_id, r.nom nom_realisateur, r.prenom prenomRealisateur " +
            "from Film f " +
                      // jointure avec les réalisateurs
            "inner join participant r on f.realisateur_id = r.id " +

            // jointure avec le titre
            "where r.prenom like :searchRealisateur% " +
            "OR r.nom like :search% ", nativeQuery = true)
    List<Film> searchByRealisateur(String searchRealisateur);

    @Query(value = "select distinct f.id, annee, duree, synopsis, f.titre, genre_id " +
            "from Film f " +
            // jointure avec les acteurs
            "full join film_acteurs fa on  f.id = fa.film_id " +
            "full join participant a on  a.id = fa.acteurs_id " +

            // jointure avec le titre
            "where a.prenom like %:searchActeur% " +
            "OR a.nom like %:search% ", nativeQuery = true)
    List<Film> searchByActeur(String searchActeur);

    @Query(value = "select distinct f.id, annee, duree, synopsis, f.titre, genre_id, realisateur_id, r.nom nom_realisateur, r.prenom prenomRealisateur " +
    "from Film f " +
            // jointure avec les acteurs
    "full join film_acteurs fa on  f.id = fa.film_id " +
    "full join participant a on  a.id = fa.acteurs_id " +

            // jointure avec les réalisateurs
     "inner join participant r on f.realisateur_id = r.id " +

            // jointure avec le titre
     "where f.titre like %:search% " +
    "OR r.prenom like %:search% " +
    "OR r.nom like %:search% " +
    "OR a.prenom like %:search% " +
    "OR a.nom like %:search% ", nativeQuery = true)
    List<Film> searchByAll(String search);

    @Query("select distinct f from Film f " +
            //jointure pour acteurs car ils passent par une table intermédiaire
    "LEFT JOIN FETCH f.acteurs a " +
            // Jpql jointure automatique, filtre par genre
    "where ( :#{#param.idGenre} is null OR f.genre.id = :#{#param.idGenre}) " +

            // filtre par réalisateur
    "and ( :#{#param.idRealisateur} is null OR f.realisateur.id = :#{#param.idRealisateur}) " +
            // filtre par acteur
    "AND ( :#{#param.idActeur} is null OR a.id = :#{#param.idActeur})  ")
    List<Film> searchByFiltre(ParametresRecherche param);
}
