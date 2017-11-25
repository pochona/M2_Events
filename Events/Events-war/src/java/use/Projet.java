/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package use;

import java.util.Date;

/**
 *
 * @author Babas
 */
public class Projet {
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
    private Boolean trois_aparitifs;
    private Boolean blanc_sec;
    private Boolean cremant;
    private Boolean champagne;

    public Projet(String nom, String coord, String manif, String type_manif, int participants, Date date, String tranche_deb, String tranche_fin, String type_presta, Boolean cocktail_prepare, Boolean trois_aparitifs, Boolean blanc_sec, Boolean cremant, Boolean champagne) {
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
        this.trois_aparitifs = trois_aparitifs;
        this.blanc_sec = blanc_sec;
        this.cremant = cremant;
        this.champagne = champagne;
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

    public void setTrois_aparitifs(Boolean trois_aparitifs) {
        this.trois_aparitifs = trois_aparitifs;
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

    public Boolean getTrois_aparitifs() {
        return trois_aparitifs;
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
    
    
    
}
