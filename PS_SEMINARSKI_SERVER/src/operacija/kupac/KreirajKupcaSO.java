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
        
        Kupac kupac = (Kupac) param;
        if (kupac.getIdKupac() <= 0)
            throw new Exception("Sistem ne moze da kreira kupca: Kupac mora imati identifikator");
        if (kupac.getImePrezime().isEmpty() || kupac.getImePrezime() == null)
            throw new Exception("Sistem ne moze da kreira kupca: Kupac mora imati ime i prezime");
        if (kupac.getMuzickoObr() == null || kupac.getMuzickoObr().getIdMuzickoObr() <= 0)
            throw new Exception("Sistem ne moze da kreira kupca: Kupac mora imati Muzicko Obrazovanje");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.dodaj((Kupac) param);
    }
    
}
