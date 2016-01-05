package fr.afcepf.al26.bibliotheque.ibusiness;

import fr.afcepf.al26.bibliotheque.entity.Utilisateur;


public interface IBusinessUtilisateur {
    String ajouterUtilisateur(Utilisateur utilisateur);

    Utilisateur getUtilisateur(String mail, String mdp);
}
