/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavkaracuna;

import domen.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Pavle
 */
public class ObrisiStavkuRacunaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof StavkaRacuna))
            throw new Exception("Sistem ne moze da obrise stavku racuna: Prosledjeni objekat nije tipa StavkaRacuna");

        StavkaRacuna stavka = (StavkaRacuna) param;
        if (stavka.getIdRacun() == null || stavka.getIdRacun() <= 0)
            throw new Exception("Sistem ne moze da obrise stavku racuna: Stavka mora imati racun");
        if (stavka.getRb() == null || stavka.getRb() <= 0)
            throw new Exception("Sistem ne moze da obrise stavku racuna: Stavka mora imati redni broj");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.obrisi((StavkaRacuna) param);
        System.out.println("Sistem je obrisao stavku racuna!");
    }

}
