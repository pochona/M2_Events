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
public class Salle implements Serializable {
    
    private int numeroSalle;
    private String typeCuisine;
    private int capacitéMax;
    private boolean estrade;
    private ArrayList<Date> occupation;
    
    public static String SIMPLE = "Simple";
    public static String IMPORTANT = "Important";
    
    public static boolean ESTRADE = true;
    public static boolean NON_ESTRADE = false;
    
    public Salle(int numeroSalle, int capacitéMax, String typeCuisine, boolean estrade){
        this.numeroSalle = numeroSalle;
        this.capacitéMax = capacitéMax;
        this.typeCuisine = typeCuisine;
        this.occupation = new ArrayList<Date>();
        
    }
    
    // Récupération de la liste des occupations
    public ArrayList<Date> getOccupation(){
        return this.occupation;
    }
    
    /**
     * setOccupation : méthode qui permet d'ajouter une date dans le calenrier de l'occupation
     * @param d : date à rajouter
     */
    public void setOccupation(Date d){
        this.occupation.add(d);
    }

    /**
     * isDisponible : verifier si une salle est dispo à une certaine date
     * @param d : date à verifier
     * @return boolean, true si la salle est dispo, false sinon
     */
    public boolean isDisponible(Date d) {
        return !this.occupation.contains(d);
    }

    public int getNumeroSalle() {
        return numeroSalle;
    }

    public void setNumeroSalle(int numeroSalle) {
        this.numeroSalle = numeroSalle;
    }

    public String getTypeCuisine() {
        return typeCuisine;
    }

    public void setTypeCuisine(String typeCuisine) {
        this.typeCuisine = typeCuisine;
    }

    public int getCapacitéMax() {
        return capacitéMax;
    }

    public void setCapacitéMax(int capacitéMax) {
        this.capacitéMax = capacitéMax;
    }
    
    public String afficheOccupation(){
        String s = "Salle n°"+this.numeroSalle+". Occupé le :";
        for(Date d: occupation){
            s += " - " +d;
        }
        return s;
    }

    public String toString(){
        return "Salle n°"+this.numeroSalle+", capacité : "+this.capacitéMax;
    }
}
