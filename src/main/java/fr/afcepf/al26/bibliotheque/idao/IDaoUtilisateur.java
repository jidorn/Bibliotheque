package fr.afcepf.al26.bibliotheque.idao;

import fr.afcepf.al26.bibliotheque.entity.Utilisateur;

import java.util.List;

/**
 * Created by Stagiaire on 04/01/2016.
 */
public interface IDaoUtilisateur {
    void ajouterUtilisateur(Utilisateur utilisateur);

    List<Utilisateur> getUtilisateurs();

    List<Utilisateur> rechercherUtilisateurParPseudo(String pseudo);

    Utilisateur getUtilisateursById(int idAuteur);
}
