package fr.afcepf.al26.bibliotheque.jdbc.dao;

import fr.afcepf.al26.bibliotheque.entity.Utilisateur;
import fr.afcepf.al26.bibliotheque.exception.BibliothequeException;
import fr.afcepf.al26.bibliotheque.idao.IDaoUtilisateur;
import fr.afcepf.al26.bibliotheque.jdbc.Al26DataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stagiaire on 04/01/2016.
 */
public class DaoUtilisateur implements IDaoUtilisateur {

    private Logger log = Logger.getLogger(DaoUtilisateur.class);
    private Connection cnx;
    private DataSource ds = new Al26DataSource();

    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        String requete = "INSERT INTO UTILISATEUR(pseudo,mail,mdp) "
                + "VALUES (?,?,?)";
        try {
            cnx = ds.getConnection();
            PreparedStatement preparedStatement = cnx.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, utilisateur.getPseudo());
            preparedStatement.setString(2, utilisateur.getMail());
            preparedStatement.setString(3, utilisateur.getMdp());
            int nb = preparedStatement.executeUpdate();
            if (nb == 1) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                rs.next();
                utilisateur.setIdUtilisateur(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                cnx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Utilisateur getUtilisateurs(String mail, String mdp) {
        String requete = "SELECT id_utilisateur, pseudo, mail, type FROM UTILISATEUR WHERE MAIL LIKE ? AND MDP LIKE ?";
        Utilisateur utilisateur = null;
        List<Utilisateur> list = new ArrayList<Utilisateur>();
        try {
            cnx = ds.getConnection();
            PreparedStatement preparedStatement = cnx.prepareStatement(requete);
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, mdp);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(hydraterUtilisateur(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                cnx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (list.size() == 1)
            utilisateur = list.get(0);

        return utilisateur;
    }

    @Override
    public boolean isMailOuPseudoExist(String nom, String valeur) throws BibliothequeException {
        int nb = 1;
        String requete = "SELECT COUNT(id_utilisateur) FROM UTILISATEUR ";
        switch (nom) {
            case "pseudo":
                requete += "WHERE pseudo LIKE ?";
                break;
            case "mail":
                requete += "WHEREW mail LIKE ?";
                break;
            default:
                throw new BibliothequeException();
        }
        try {
            cnx = ds.getConnection();
            PreparedStatement preparedStatement = cnx.prepareStatement(requete);
            preparedStatement.setString(1, valeur);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            nb = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                cnx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (nb ==0)? false:true;
    }

    private Utilisateur hydraterUtilisateur(ResultSet rs) throws SQLException {
        int idUtilisateur = rs.getInt("id_utilisateur");
        String mail = rs.getString("mail");
        String pseudo = rs.getString("pseudo");
        String type = rs.getString("type");
        return new Utilisateur(idUtilisateur, pseudo, mail, type);
    }

}
