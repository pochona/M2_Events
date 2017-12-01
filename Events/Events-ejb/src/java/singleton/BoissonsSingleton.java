/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import messages.Boissons;
import messages.Projet;


/**
 *
 * @author Amaury_PC
 */
@Singleton
@LocalBean
public class BoissonsSingleton {
    
    private HashMap<String, Boissons> boissons;
 
    static final Logger logger = Logger.getLogger("BoissonsSingleton");
    
   @PostConstruct
    public void initialiser(){
        boissons = new HashMap<>();
        Boissons b1 = new Boissons(Boissons.EAU, 200);
        Boissons b2 = new Boissons(Boissons.JUSFRUIT, 100);
        Boissons b3 = new Boissons(Boissons.BLANCSEC, 20);
        Boissons b4 = new Boissons(Boissons.ROUGE, 30);
        Boissons b5 = new Boissons(Boissons.CREMANT, 30);
        Boissons b6 = new Boissons(Boissons.CHAMPAGNE, 20);
        boissons.put(Boissons.EAU, b1);
        boissons.put(Boissons.JUSFRUIT, b2);
        boissons.put(Boissons.BLANCSEC, b3);
        boissons.put(Boissons.ROUGE, b4);
        boissons.put(Boissons.CREMANT, b5);
        boissons.put(Boissons.CHAMPAGNE, b6);
    }
     
    public void reserverBoissons(Projet p) {
        // Coefficient de division selon le type de projet 
        int coefficient = 3;
        // selon le type de projet, je n'ai pas besoin d'autant de bouteille
        if(p.getType_presta().equals(Projet.PRESTA_COCKTAIL)){
            // Une bouteille pour 3 personnes
            coefficient = 3;
        } else if( p.getType_presta().equals(Projet.PRESTA_LUNCH)){
            // Une bouteille pour 2 personnes
            coefficient = 2;
        }
        // Je check le stock pour les boissons obligatoires
        // Une eau par personne
        this.gererStock(boissons.get(Boissons.EAU), p, 1);
        // un jus par personne
        this.gererStock(boissons.get(Boissons.JUSFRUIT), p, 1);
        
       // boissons paramétrés dans le formulaire
       if(p.hasBlanc_sec()){
           this.gererStock(boissons.get(Boissons.BLANCSEC), p, coefficient);
       }
       if(p.hasChampagne()){
           this.gererStock(boissons.get(Boissons.CHAMPAGNE), p, coefficient);
       }
       if(p.hasCremant()){
           this.gererStock(boissons.get(Boissons.CREMANT), p, coefficient);
       }
       if(p.hasRouge()){
           this.gererStock(boissons.get(Boissons.ROUGE), p, coefficient);
       }
    }
    
    public void gererStock(Boissons b, Projet p, int coefficient){
        int qteCommande = p.getParticipants() / coefficient;
        if(b.getQteStock() > qteCommande){
                b.enleverStock(qteCommande);
                logger.log(Level.INFO, "---Boisson reservée : "+b.getNom()+" ("+qteCommande+")---", "Message");
            } else {
                // Si j'ai pas assez de boissons, je commande d'abord du nouveau stock avant de pouvoir destocker
                b.commanderStock(qteCommande);
                logger.log(Level.INFO, "/!\\ Produit manquant. Commande de produit " + b.getNom(), b);
                b.enleverStock(qteCommande);
                logger.log(Level.INFO, "---Boisson reservée : "+b.getNom()+" ("+qteCommande+")---", "Message");
            }
    }
}
