package fr.afcepf.al26.bibliotheque.idao;

import fr.afcepf.al26.bibliotheque.entity.Utilisateur;


/**
 * Created by Stagiaire on 04/01/2016.
 */
public interface IDaoUtilisateur {
    void ajouterUtilisateur(Utilisateur utilisateur);

    Utilisateur getUtilisateurs(String mail,String mdp);
}
