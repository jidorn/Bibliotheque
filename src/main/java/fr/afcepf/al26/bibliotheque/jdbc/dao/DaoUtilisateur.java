package fr.afcepf.al26.bibliotheque.jdbc.dao;

import fr.afcepf.al26.bibliotheque.entity.Utilisateur;
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
                int idUtilisateur = rs.getInt(1);
                utilisateur.setIdUtilisateur(idUtilisateur);
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
    public List<Utilisateur> getUtilisateurs() {
        List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
        try {
            cnx = ds.getConnection();
            String requete = "Select * from Utilisateur";
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listeUtilisateurs.add(
                        new Utilisateur(
                                rs.getString("pseudo"),
                                rs.getString("mail"),
                                rs.getString("mdp"))
                );

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
        return listeUtilisateurs;
    }

    @Override
    public List<Utilisateur> rechercherUtilisateurParPseudo(String pseudo) {
        List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();

        try {
            cnx = ds.getConnection();
            String requete = "Select * from Utilisateur where pseudo like ?";
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            pstmt.setString(1, "%" + pseudo + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listeUtilisateurs.add(
                        new Utilisateur(
                                rs.getString("pseudo"),
                                rs.getString("mail"),
                                rs.getString("mdp")
                        )
                );

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


        return listeUtilisateurs;
    }

    @Override
    public Utilisateur getUtilisateursById(int idUtilisateur) {
        Utilisateur utilisateur = null;
        ArrayList<Utilisateur> liste = new ArrayList<Utilisateur>();
        String requete = "Select * from Utilisateur where id_utilisateur= ?";
        try {
            cnx = ds.getConnection();
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            pstmt.setInt(1, idUtilisateur);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                liste.add(
                        new Utilisateur(
                                rs.getString("pseudo"),
                                rs.getString("mail"),
                                rs.getString("mdp")
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cnx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (liste.size() == 1) {
            utilisateur = liste.get(0);
        }

        return utilisateur;
    }
}
