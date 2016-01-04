package fr.afcepf.al26.bibliotheque.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import fr.afcepf.al26.bibliotheque.entity.Livre;
import fr.afcepf.al26.bibliotheque.idao.IDaoLivre;
import fr.afcepf.al26.bibliotheque.jdbc.Al26DataSource;

public class DaoLivre implements IDaoLivre {

	private Connection cnx;
	private DataSource ds;
	@Override
	public void ajouterLivre(Livre livre) {
		ds = new Al26DataSource();
		try {
			cnx = ds.getConnection();
			String requete = "INSERT INTO LIVRE "
					+ "(titre,nombre_pages,genre,date_parution,"
					+ "edition,id_auteur)" + "VALUES (?,?,?,?,?,?)";
			PreparedStatement pstmt = cnx.prepareStatement(requete,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, livre.getTitre());
			pstmt.setInt(2, livre.getNbPages());
			pstmt.setString(3, livre.getGenre());
			pstmt.setDate(4, new Date(livre.getDateParution().getTime()));
			pstmt.setString(5, livre.getEdition());
			pstmt.setInt(6, livre.getAuteur().getIdAuteur());

			int nb = pstmt.executeUpdate();

			if (nb == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				livre.setIdLivre(rs.getInt(1));
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
	public List<Livre> getLivreParAuteur(int idAuteur) {
        List<Livre> livreList = new ArrayList<Livre>();
		String requete = "SELECT * FROM LIVRE WHERE id_auteur = ?";
		ds = new Al26DataSource();
		try {
			cnx = ds.getConnection();
			PreparedStatement preparedStatement = cnx.prepareStatement(requete);
			preparedStatement.setInt(1,idAuteur);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()){
                livreList.add(hydrateLivre(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        finally {
            try {
                cnx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return livreList;
	}

    private Livre hydrateLivre(ResultSet rs) throws SQLException {
        Livre livre = new Livre();
        java.util.Date date = new java.util.Date(rs.getDate("date_parution").getTime());
        livre.setDateParution(date);
        livre.setIdLivre(rs.getInt("id_livre"));
        livre.setNbPages(rs.getInt("nombre_pages"));
        livre.setEdition(rs.getString("edition"));
        livre.setTitre(rs.getString("titre"));
        return livre;
    }
}
