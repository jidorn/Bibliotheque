package fr.afcepf.al26.bibliotheque.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import fr.afcepf.al26.bibliotheque.entity.Auteur;
import fr.afcepf.al26.bibliotheque.idao.IDaoAuteur;
import fr.afcepf.al26.bibliotheque.jdbc.Al26DataSource;

public class DaoAuteur implements IDaoAuteur {

    private Connection cnx;
    private DataSource ds = new Al26DataSource();

    @Override
    public void ajouterAuteur(Auteur auteur) {


        String requete = "INSERT INTO AUTEUR(nom,prenom) "
                + "VALUES (?,?)";
        try {
            cnx = ds.getConnection();
            PreparedStatement pstmt = cnx.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, auteur.getNom());
            pstmt.setString(2, auteur.getPrenom());

            int nb = pstmt.executeUpdate();

            if (nb == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                rs.next();
                int idAuteur = rs.getInt(1);
                auteur.setIdAuteur(idAuteur);
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
    public List<Auteur> rechercherAuteurParNom(String nom) {
        List<Auteur> listeAuteurs = new ArrayList<Auteur>();

        try {
            cnx = ds.getConnection();
            String requete = "Select * from auteur where nom like ?";
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            pstmt.setString(1, "%" + nom + "%");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listeAuteurs.add(
                        new Auteur(
                                rs.getInt("id_auteur"),
                                rs.getString("nom"),
                                rs.getString("prenom")
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


        return listeAuteurs;
    }

    @Override
    public List<Auteur> getAuteurs() {
        List<Auteur> listeAuteurs = new ArrayList<Auteur>();

        try {
            cnx = ds.getConnection();
            String requete = "Select * from auteur";
            PreparedStatement pstmt = cnx.prepareStatement(requete);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listeAuteurs.add(
                        new Auteur(
                                rs.getInt("id_auteur"),
                                rs.getString("nom"),
                                rs.getString("prenom")
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


        return listeAuteurs;
    }

    @Override
    public Auteur getAuteursById(int idAuteur) {
        Auteur auteur = null;
        ArrayList<Auteur> liste = new ArrayList<Auteur>();
        String requete = "Select * from AUTEUR where id_auteur= ?";
        try {
            cnx = ds.getConnection();
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            pstmt.setInt(1, idAuteur);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                liste.add(
                        new Auteur(
                                rs.getInt("id_auteur"),
                                rs.getString("nom"),
                                rs.getString("prenom")
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
            auteur = liste.get(0);
        }

        return auteur;
    }

}

