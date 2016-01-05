package fr.afcepf.al26.bibliotheque.business;

import fr.afcepf.al26.bibliotheque.entity.Utilisateur;
import fr.afcepf.al26.bibliotheque.exception.BibliothequeException;
import fr.afcepf.al26.bibliotheque.ibusiness.IBusinessUtilisateur;
import fr.afcepf.al26.bibliotheque.idao.IDaoUtilisateur;
import fr.afcepf.al26.bibliotheque.jdbc.dao.DaoUtilisateur;

/**
 * Created by Stagiaire on 05/01/2016.
 */
public class BusinessUtilisateur implements IBusinessUtilisateur {

    private IDaoUtilisateur daoUtilisateur = new DaoUtilisateur();

    @Override
    public String ajouterUtilisateur(Utilisateur utilisateur) {
        String message = "";
        try {
            boolean pseudoExist = daoUtilisateur.isMailOuPseudoExist("pseudo", utilisateur.getPseudo());
            if (pseudoExist)
                message = "le pseudo est utilisé ";
            boolean mailExist = daoUtilisateur.isMailOuPseudoExist("mail", utilisateur.getMail());
            if (mailExist)
                message += "le mail est utilisé";
            if (!pseudoExist && !mailExist) {
                daoUtilisateur.ajouterUtilisateur(utilisateur);
                message = "Utilisateur bien enregistré";
            }
        } catch (BibliothequeException e) {
            e.printStackTrace();
        }

        return message;
    }

    @Override
    public Utilisateur getUtilisateur(String mail, String mdp) {

        return daoUtilisateur.getUtilisateurs(mail, mdp);
    }
}
