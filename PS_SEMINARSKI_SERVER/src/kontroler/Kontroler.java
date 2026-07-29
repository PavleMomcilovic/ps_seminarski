/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Prodavac;
import operacija.prijava.PrijaviProdavcaSO;

/**
 *
 * @author pavle
 */
public class Kontroler {
    private static Kontroler instanca;
    
    private Kontroler() {
        
    }
    
    public static Kontroler getInstanca() {
        if (instanca == null)
            return new Kontroler();
        return instanca;
    }
    
    public Prodavac prijaviProdavca(Prodavac p) throws Exception {
        PrijaviProdavcaSO operacija = new PrijaviProdavcaSO();
        operacija.izvrsi(p, null);
        System.out.println("KLASA KONTROLER: " + operacija.getProdavac());
        return operacija.getProdavac();
    }
}
