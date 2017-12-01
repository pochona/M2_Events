/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ejb.Singleton;
import messages.Projet;

/**
 *
 * @author Amaury_PC
 */
@Singleton
public class GestionProjet implements GestionProjetLocal {

    @Override
    public void traiterDemande(Projet projet) {
        
    }

    @Override
    public void traiterSalleAttribue(Projet projet) {
        //
    }

    @Override
    public void traiterAnnulation(Projet projet) {
        projet.annuler();
    }

}
