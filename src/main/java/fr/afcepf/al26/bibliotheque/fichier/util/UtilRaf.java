package fr.afcepf.al26.bibliotheque.fichier.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ResourceBundle;

public class UtilRaf {

	private static File fichierAuteur;
	private static File fichierLivre;
	
	public static final int POIDSINT = 4;
	public static final int POIDSCHAR = 2;
	public static final int TAILLENOM = 45;
	public static final int TAILLEPRENOM = 45;
	public static final int TAILLETOTALEAUTEUR = (TAILLENOM+TAILLEPRENOM)*POIDSCHAR+POIDSINT;
	public static final int POSITIONDEBUTPRENOM = TAILLENOM *POIDSCHAR+POIDSINT;
	public static File getFichierAuteur() {
		return fichierAuteur;
	}

	public static File getFichierLivre() {
		return fichierLivre;
	}

	static {
	ResourceBundle rb = ResourceBundle.getBundle("fichierBinaireBibliotheque");
	fichierAuteur = new File(rb.getString("auteur"));
	fichierLivre= new File(rb.getString("livre"));
	}
	
	private UtilRaf()
	{
		
	}
	
	public static String formaterString(String stringAFormater,int tailleMaxi){
		
		int tailleString = stringAFormater.length();
		for(;tailleString<tailleMaxi;tailleString++)
		{
			stringAFormater+=" ";
		}
		
		return stringAFormater;
	}

	public static int getLastId(RandomAccessFile raf, int tailleTotale) throws IOException {
		long nb = raf.length()/tailleTotale;
		nb++;
		return (int)nb;
	}

	public static String lireString(RandomAccessFile raf, int nbChar) throws IOException {
		String string ="";
		for(int i=0; i<nbChar;i++)
		{
			string +=raf.readChar();
		}
		return string.trim();
	}
}
