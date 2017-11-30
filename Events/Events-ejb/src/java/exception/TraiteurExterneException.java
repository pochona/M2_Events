/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import messages.Projet;

/**
 *
 * @author Amaury_PC
 */
public class TraiteurExterneException extends Exception {
    
    public Projet p;
    public String erreur;
    
    public TraiteurExterneException(Projet p, String s){
        this.p = p;
        this.erreur = "Erreur de réservation du traiteur externe pour le projet : " +p.getReference() + " " + s;
    }
    
    public TraiteurExterneException(Projet p){
        this.p = p;
        this.erreur = "Erreur de réservation du traiteur externe pour le projet : " +p.getReference();
    }
    
    public String toString(){
        return this.erreur;
    }
}
