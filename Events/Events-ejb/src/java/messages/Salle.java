/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import java.io.Serializable;

/**
 *
 * @author Amaury_PC
 */
public class Salle implements Serializable {
    
    private int numeroSalle;
    private String typeCuisine;
    private int capacitéMax;
    private boolean estrade;
    private boolean disponible;
    
    public Salle(int numeroSalle, int capacitéMax, String typeCuisine, boolean estrade){
        this.numeroSalle = numeroSalle;
        this.capacitéMax = capacitéMax;
        this.typeCuisine = typeCuisine; //ENUM ? simple/importante
        this.disponible = true;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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

    
}
