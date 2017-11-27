/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ejb.Local;
import messages.Projet;

/**
 *
 * @author Amaury_PC
 */
@Local
public interface GestionProjetLocal {
    
    public void traiterDemande(Projet projet);
    
    public void traiterSalleAttribue(Projet projet);
    
    public void traiterAnnulation(Projet projet);
}
