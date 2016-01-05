package fr.afcepf.al26.bibliotheque.exception;

/**
 * Created by Stagiaire on 05/01/2016.
 */
public class BibliothequeException extends Exception {

    public BibliothequeException() {
        super("problème lors de la vérification de l'unicité du mail ou du pseudo");
    }

    public BibliothequeException(String message) {
        super(message);
    }
}
