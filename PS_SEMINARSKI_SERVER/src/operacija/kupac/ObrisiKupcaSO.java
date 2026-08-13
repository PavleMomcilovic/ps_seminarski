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
public class ObrisiKupcaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Kupac))
            throw new Exception("Sistem ne moze da nadje kupce po zadatim kriterijumima: Prosledjeni objekat nije tipa Kupac");
        if (((Kupac) param).getIdKupac() <= 0)
            throw new Exception("Sistem ne moze da nadje kupce po zadatim kriterijumima: Kupac mora imati identifikator");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.obrisi((Kupac) param);
    }
    
}
