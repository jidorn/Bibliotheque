package fr.afcepf.al26.bibliotheque.idao;

import java.util.List;

import fr.afcepf.al26.bibliotheque.entity.Auteur;

public interface IDaoAuteur {

	void ajouterAuteur(Auteur auteur);

	List<Auteur> rechercherAuteurParNom(String nom);

	List<Auteur> getAuteurs();
	
	Auteur getAuteursById(int idAuteur);

}