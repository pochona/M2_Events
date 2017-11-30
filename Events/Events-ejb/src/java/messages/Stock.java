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
public class Stock implements Serializable {
    
    private int qteStock;
    
    
    public Stock(int qteStock){
        this.qteStock = qteStock;
        
    }
    
    public int getStock(){
        return this.qteStock;
    }
    
    public void ajouterStock(int i){
        this.qteStock += i;
    }
    
    public void enleverStock(int i){
        this.qteStock -= i;
    }
}
