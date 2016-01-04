package fr.afcepf.al26.bibliotheque.idao;

import fr.afcepf.al26.bibliotheque.entity.Livre;

import java.util.List;

public interface IDaoLivre {

	 void ajouterLivre(Livre livre);
	List<Livre> getLivreParAuteur(int idAuteur);
}