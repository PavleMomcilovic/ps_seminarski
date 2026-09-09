/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import domen.ApstraktniDomenskiObjekat;
import domen.Racun;
import domen.StavkaRacuna;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class ObrisiRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Racun))
            throw new Exception("Sistem ne moze da obrise racun: Prosledjeni objekat nije tipa Racun");
        if (((Racun) param).getIdRacun() == null || ((Racun) param).getIdRacun() <= 0)
            throw new Exception("Sistem ne moze da obrise racun: Racun mora imati identifikator");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Racun racun = (Racun) param;

        StavkaRacuna kriterijum = new StavkaRacuna();
        kriterijum.setIdRacun(racun.getIdRacun());
        String uslov = " WHERE " + kriterijum.generisiKriterijumPretrazivanja();
        List<ApstraktniDomenskiObjekat> stavke = broker.uzmiSve(kriterijum, uslov);
        for (ApstraktniDomenskiObjekat stavka : stavke) {
            broker.obrisi(stavka);
        }

        broker.obrisi(racun);
    }
}
