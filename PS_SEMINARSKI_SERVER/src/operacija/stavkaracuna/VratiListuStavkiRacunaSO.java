/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavkaracuna;

import domen.ApstraktniDomenskiObjekat;
import domen.StavkaRacuna;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Pavle
 */
public class VratiListuStavkiRacunaSO extends ApstraktnaGenerickaOperacija {

    private List<StavkaRacuna> rezultat;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof StavkaRacuna))
            throw new Exception("Sistem ne moze da nadje stavke racuna po zadatim kriterijumima: Prosledjeni objekat nije tipa StavkaRacuna");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        StavkaRacuna kriterijum = (StavkaRacuna) param;
        String uslov = kriterijum.generisiKriterijumPretrazivanja();
        if (!uslov.isEmpty()) {
            uslov = " WHERE " + uslov;
        }
        List<ApstraktniDomenskiObjekat> lista = broker.uzmiSve(kriterijum, uslov);
        if (lista.isEmpty()) {
            rezultat = null;
            return;
        }
        rezultat = new ArrayList<>();
        for (ApstraktniDomenskiObjekat objekat : lista) {
            rezultat.add((StavkaRacuna) objekat);
        }
        System.out.println("Sistem je nasao stavke racuna po zadatim kriterijumima!");
    }

    public List<StavkaRacuna> getRezultat() {
        return rezultat;
    }
}
