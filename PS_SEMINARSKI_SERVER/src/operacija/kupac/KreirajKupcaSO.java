/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupac;

import domen.Kupac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class KreirajKupcaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Kupac))
            throw new Exception("Sistem ne moze da kreira kupca: Prosledjeni objekat nije tipa Kupac");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Kupac k = (Kupac) param;
        Long idKupac = broker.uzmiGenerisaniKljuc(k);
        k.setIdKupac(idKupac);
    }
    
}
