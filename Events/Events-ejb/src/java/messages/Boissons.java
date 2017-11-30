/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Amaury_PC
 */
public class Boissons implements Serializable {
    
    public static String EAU = "eau";
    public static String JUSFRUIT = "jus_fruit";
    public static String BLANCSEC = "blanc_sec";
    public static String ROUGE = "rouge";
    public static String CREMANT = "cremant";
    public static String CHAMPAGNE = "champagne";
                                        
    private String nomBoisson;
    private Stock stock;
    
    
    public Boissons(String nomBoisson, int qteStockInitial){
        this.nomBoisson = nomBoisson;
        this.stock = new Stock(qteStockInitial);
        
    }
    
    public String getNom(){
        return this.nomBoisson;
    }
    
    public int getQteStock(){
        return this.stock.getStock();
    }
    
    public void enleverStock(int i){
        this.stock.enleverStock(i);
    }
    
    public void ajouterStock(int i){
        this.stock.ajouterStock(i);
    }
    
    public void commanderStock(){
        this.ajouterStock(50);
    }
}
