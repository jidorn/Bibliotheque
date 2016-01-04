package fr.afcepf.al26.bibliotheque.entity;

/**
 * Created by Stagiaire on 04/01/2016.
 */
public class Utilisateur {
    private int idUtilisateur;
    private String pseudo,mail,mdp,type;

    public Utilisateur() {
    }

    public Utilisateur(String pseudo, String mail, String mdp) {
        this.pseudo = pseudo;
        this.mail = mail;
        this.mdp = mdp;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Utilisateur =  idUtilisateur=" + idUtilisateur +
                ", pseudo='" + pseudo;
    }
}
