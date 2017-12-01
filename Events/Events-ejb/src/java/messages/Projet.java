/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amaury_PC
 */
public class Projet implements Serializable {
    
    public static String PRESTA_COCKTAIL = "cocktail_seul";
    public static String PRESTA_LUNCH = "lunch";
    public static String PRESTA_REPAS = "repas_assis";
    
    public static String STATUT_EN_COURS = "En cours";
    public static String STATUT_EN_ERREUR = "En erreur";
    public static String STATUT_ANNULE = "Annulé";
    
    private String reference;
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
    private String statut;
    private int numeroSalle;



    private Salle salle;
    private ArrayList<Employe> employes;


    public Projet(String nom, String coord, String manif, String type_manif, int participants, Date date, String tranche_deb, String tranche_fin, String type_presta, Boolean cocktail_prepare, Boolean trois_aperitifs, Boolean blanc_sec, Boolean cremant, Boolean champagne, Boolean rouge) {
       /*
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "nom "+ nom, nom);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "coord "+ coord, coord);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "manif "+ manif, manif);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "type_manif "+ type_manif, type_manif);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "participants "+ participants, participants);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "date "+ date, date);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "tranche_deb "+ tranche_deb, tranche_deb);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "tranche_fin "+ tranche_fin, tranche_fin);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "type_presta "+ type_presta, type_presta);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "cocktail_prepare "+ cocktail_prepare, cocktail_prepare);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "trois_aperitifs "+ trois_aperitifs, trois_aperitifs);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "blanc_sec "+ blanc_sec, blanc_sec);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "cremant "+ cremant, cremant);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "champagne "+ champagne, champagne);
        Logger.getLogger(Projet.class.getName()).log(Level.INFO, "rouge "+ rouge, rouge);
        */
                
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
        this.statut = Projet.STATUT_EN_COURS;
        this.reference = UUID.randomUUID().toString();
    } 

  
    public Projet() {
     
    }
    
    public String toString(){
        return "Projet "+this.reference+ ", Nombre de personnes : "+this.participants;
    }
    
    public void annuler(){
        this.statut = Projet.STATUT_ANNULE;
    }
    
    public void erreur(){
        this.statut = Projet.STATUT_EN_ERREUR;
    }

    // Verifier si un projet possède une salle
    public boolean hasSalle(){
        return this.salle != null;
    }
    
    public int getNumeroSalle() {
        return numeroSalle;
    }

    public void setNumeroSalle(int numeroSalle) {
        this.numeroSalle = numeroSalle;
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

    public String getReference(){
        return this.reference;
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
        this.numeroSalle = salle.getNumeroSalle();
        this.salle = salle;
    }

    public ArrayList<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(ArrayList<Employe> employes) {
        this.employes = employes;
    }
    
    public Boolean hasBlanc_sec() {
        return blanc_sec != null && blanc_sec;
    }

    public Boolean hasCremant() {
        return cremant != null && cremant;
    }

    public Boolean hasChampagne() {
        return champagne != null && champagne;
    }

    public Boolean hasRouge() {
        return rouge != null && rouge;
    }
    
    
    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    
    
}
