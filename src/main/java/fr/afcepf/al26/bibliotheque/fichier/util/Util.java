package fr.afcepf.al26.bibliotheque.fichier.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

public class Util {


	private File fichierAuteur;
	private File fichierLivre;
	private File fichierIndexAuteur;
	private File fichierIndexLivre;

	public Util()
	{
		ResourceBundle rb = ResourceBundle.getBundle("fichierBibliotheque");
		String fichierAuteurChemin =rb.getString("auteur");

		fichierAuteur = new File(fichierAuteurChemin);
		String fichierLivreChemin =rb.getString("livre");
		fichierLivre = new File(fichierLivreChemin);

		fichierIndexAuteur = new File(rb.getString("auteurIndex"));
		fichierIndexLivre = new File(rb.getString("livreIndex"));

		verifierFichiersIndex();
	}

	private void verifierFichiersIndex() {
		verifierFichierIndex(fichierIndexAuteur);
		verifierFichierIndex(fichierIndexLivre);
	}

	private void verifierFichierIndex(File fichierIndex) {
		if(!fichierIndex.exists())
		{
			initialiserFichier(fichierIndex);
		}
	}

	private void initialiserFichier(File fichierIndex) {
		try {
			FileWriter fw = new FileWriter(fichierIndex);
			fw.write(""+0);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public File getFichierAuteur() {
		return fichierAuteur;
	}

	public File getFichierLivre() {
		return fichierLivre;
	}

	public int getIndexAuteur()
	{
		return getIndexFichier(fichierIndexAuteur);
	}

	public int getIndexLivre()
	{
		return getIndexFichier(fichierIndexLivre);
	}

	private int getIndexFichier(File fichierIndex)
	{
		int nb=0; 

		try {
			nb = getIndexDansFichier(fichierIndex);
			nb++;
			setIndexDansFichier(nb,fichierIndex);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nb;
	}

	private void setIndexDansFichier(int nb,File fichierIndex) throws IOException {
		FileWriter fw = new FileWriter(fichierIndex);
		fw.write(""+nb);
		fw.close();
	}

	private int getIndexDansFichier(File fichierIndex) throws FileNotFoundException,
	IOException {
		int nb = 0;
		FileReader fr = new FileReader(fichierIndex);
		BufferedReader br=  new BufferedReader(fr);
		String ligne = br.readLine();

		if(ligne!=null)
		{
			nb = Integer.parseInt(ligne);
		}
		fr.close();
		return nb;
	}

}
