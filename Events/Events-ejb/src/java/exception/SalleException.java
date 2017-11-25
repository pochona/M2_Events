/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Amaury_PC
 */
public class SalleException extends Exception {
    
    public String erreur;
    
    public SalleException(String s){
        this.erreur = "Erreur de salle : " +s;
    }
    
    public SalleException(){
        this.erreur = "Erreur de salle.";
    }
    
    public String toString(){
        return this.erreur;
    }
}
