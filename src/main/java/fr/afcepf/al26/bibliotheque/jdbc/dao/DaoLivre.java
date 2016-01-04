package fr.afcepf.al26.bibliotheque.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import fr.afcepf.al26.bibliotheque.entity.Livre;
import fr.afcepf.al26.bibliotheque.idao.IDaoLivre;
import fr.afcepf.al26.bibliotheque.jdbc.Al26DataSource;

public class DaoLivre implements IDaoLivre {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * fr.afcepf.al26.bibliotheque.jdbc.dao.IDaoLivre#ajouterLivre(fr.afcepf
	 * .al26.bibliotheque.entity.Livre)
	 */
	@Override
	public void ajouterLivre(Livre livre) {

		Connection cnx = null;
		DataSource ds = new Al26DataSource();

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
