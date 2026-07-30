/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.prodavac;

import domen.Prodavac;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class PrijaviProdavcaSO extends ApstraktnaGenerickaOperacija {
    private Prodavac prodavac;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Prodavac)) {
            throw new Exception("Nemoguca prijava prodavca!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Prodavac> sviProdavaci = broker.uzmiSve(param, kljuc);
        System.out.println("KLASA PRIJAVIPRODAVCASO: " + sviProdavaci);
        
        for (Prodavac p : sviProdavaci) {
            if (p.getUsername().equals(((Prodavac) param).getUsername()) && p.getPassword().equals(((Prodavac) param).getPassword())) {
                prodavac = p;
                return;
            }
        }
        
        prodavac = null;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }
}
