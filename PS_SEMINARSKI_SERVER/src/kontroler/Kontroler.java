/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Prodavac;

/**
 *
 * @author pavle
 */
public class Kontroler {
    private static Kontroler instanca;
    
    private Kontroler() {
        
    }
    
    private static Kontroler getKontroler() {
        if (instanca == null)
            return new Kontroler();
        return instanca;
    }
    
    public Prodavac prijaviProdavca() {
        return null;
    }
}
