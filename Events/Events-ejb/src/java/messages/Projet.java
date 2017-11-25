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
    private String coord;
    private String manif;
    private String type_manif;
    private int participants;
    private Date date;
    private String tranche_deb;
    private String tranche_fin;
    private String type_presta;
    private Boolean cocktail_prepare;
    private Boolean trois_aperitifs;
    private Boolean blanc_sec;
    private Boolean cremant;
    private Boolean champagne;
    private Boolean rouge;
    
    private String refProjet;
    
    private Salle salle;
    private ArrayList<Employe> employes;
    
        public Projet (String refProjet, String nom, int nbParticipant){
        this.refProjet = refProjet;
        this.nom = nom;
        this.date = new Date();
        this.participants = nbParticipant;
    }

    public Projet(String nom, String coord, String manif, String type_manif, int participants, Date date, String tranche_deb, String tranche_fin, String type_presta, Boolean cocktail_prepare, Boolean trois_aperitifs, Boolean blanc_sec, Boolean cremant, Boolean champagne, Boolean rouge) {
        this.nom = nom;
        this.coord = coord;
        this.manif = manif;
        this.type_manif = type_manif;
        this.participants = participants;
        this.date = date;
        this.tranche_deb = tranche_deb;
        this.tranche_fin = tranche_fin;
        this.type_presta = type_presta;
        this.cocktail_prepare = cocktail_prepare;
        this.trois_aperitifs = trois_aperitifs;
        this.blanc_sec = blanc_sec;
        this.cremant = cremant;
        this.champagne = champagne;
        this.rouge = rouge;
    } 

  
    public Projet() {
     
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public void setManif(String manif) {
        this.manif = manif;
    }

    public void setType_manif(String type_manif) {
        this.type_manif = type_manif;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTranche_deb(String tranche_deb) {
        this.tranche_deb = tranche_deb;
    }

    public void setTranche_fin(String tranche_fin) {
        this.tranche_fin = tranche_fin;
    }

    public void setType_presta(String type_presta) {
        this.type_presta = type_presta;
    }

    public void setCocktail_prepare(Boolean cocktail_prepare) {
        this.cocktail_prepare = cocktail_prepare;
    }

    public void setTrois_aparitifs(Boolean trois_aperitifs) {
        this.trois_aperitifs = trois_aperitifs;
    }

    public void setBlanc_sec(Boolean blanc_sec) {
        this.blanc_sec = blanc_sec;
    }

    public void setCremant(Boolean cremant) {
        this.cremant = cremant;
    }

    public void setChampagne(Boolean champagne) {
        this.champagne = champagne;
    }

    public String getNom() {
        return nom;
    }

    public String getCoord() {
        return coord;
    }

    public String getManif() {
        return manif;
    }

    public String getType_manif() {
        return type_manif;
    }

    public int getParticipants() {
        return participants;
    }

    public Date getDate() {
        return date;
    }

    public String getTranche_deb() {
        return tranche_deb;
    }

    public String getTranche_fin() {
        return tranche_fin;
    }

    public String getType_presta() {
        return type_presta;
    }

    public Boolean getCocktail_prepare() {
        return cocktail_prepare;
    }

    public Boolean getTrois_aperitifs() {
        return trois_aperitifs;
    }

    public Boolean getBlanc_sec() {
        return blanc_sec;
    }

    public Boolean getCremant() {
        return cremant;
    }

    public Boolean getChampagne() {
        return champagne;
    }
    
    public void setRouge(Boolean rouge) {
        this.rouge = rouge;
    }

    public Boolean getRouge() {
        return rouge;
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
