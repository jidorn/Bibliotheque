package fr.afcepf.al26.bibliotheque.entity;

import java.util.Date;

public class Livre {

	
	private int idLivre;
	private String titre;
	private int nbPages;
	private String edition;
	private String genre;
	private Date dateParution;
	private Auteur auteur;
	
	public int getIdLivre() {
		return idLivre;
	}
	public void setIdLivre(int idLivre) {
		this.idLivre = idLivre;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getNbPages() {
		return nbPages;
	}
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String editeur) {
		this.edition = editeur;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Date getDateParution() {
		return dateParution;
	}
	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}
	public Auteur getAuteur() {
		return auteur;
	}
	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}
	public Livre(int idLivre, String titre, int nbPages, String editeur,
			String genre, Date dateParution, Auteur auteur) {

		this.idLivre = idLivre;
		this.titre = titre;
		this.nbPages = nbPages;
		this.edition = editeur;
		this.genre = genre;
		this.dateParution = dateParution;
		this.auteur = auteur;
	}
	public Livre(String titre, int nbPages, String editeur, String genre,
			Date dateParution, Auteur auteur) {

		this.titre = titre;
		this.nbPages = nbPages;
		this.edition = editeur;
		this.genre = genre;
		this.dateParution = dateParution;
		this.auteur = auteur;
	}
	public Livre(int idLivre, String titre, int nbPages, String editeur,
			String genre, Date dateParution) {

		this.idLivre = idLivre;
		this.titre = titre;
		this.nbPages = nbPages;
		this.edition = editeur;
		this.genre = genre;
		this.dateParution = dateParution;
	}
	public Livre(String titre, int nbPages, String editeur, String genre,
			Date dateParution) {

		this.titre = titre;
		this.nbPages = nbPages;
		this.edition = editeur;
		this.genre = genre;
		this.dateParution = dateParution;
	}
	public Livre() {
	}
	@Override
	public String toString() {
		return "Livre [idLivre=" + idLivre + ", titre=" + titre + ", nbPages="
				+ nbPages + ", edition=" + edition + ", genre=" + genre
				+ ", dateParution=" + dateParution + ", auteur=" + auteur + "]";
	}
	
}
