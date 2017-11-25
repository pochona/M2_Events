/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Amaury_PC
 */
public class Projet implements Serializable {
    
    private String nom;
    private String coordonnees;
    private String refProjet;
    private int nbParticipant;
    private Date date;
    
    private Salle salle;
    private ArrayList<Employe> employes;
    
    public Projet (String refProjet, String nom, int nbParticipant){
        this.refProjet = refProjet;
        this.nom = nom;
        this.date = new Date();
        this.nbParticipant = nbParticipant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(String coordonnees) {
        this.coordonnees = coordonnees;
    }

    public String getRefProjet() {
        return refProjet;
    }

    public void setRefProjet(String refProjet) {
        this.refProjet = refProjet;
    }

    public int getNbParticipant() {
        return nbParticipant;
    }

    public void setNbParticipant(int nbParticipant) {
        this.nbParticipant = nbParticipant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public ArrayList<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(ArrayList<Employe> employes) {
        this.employes = employes;
    }
    
    
}
