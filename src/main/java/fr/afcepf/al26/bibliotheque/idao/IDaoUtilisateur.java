package fr.afcepf.al26.bibliotheque.idao;

import fr.afcepf.al26.bibliotheque.entity.Utilisateur;
import fr.afcepf.al26.bibliotheque.exception.BibliothequeException;


/**
 * Created by Stagiaire on 04/01/2016.
 */
public interface IDaoUtilisateur {
    void ajouterUtilisateur(Utilisateur utilisateur);

    Utilisateur getUtilisateurs(String mail,String mdp);

    boolean isMailOuPseudoExist(String nom, String valeur)throws BibliothequeException;
}
