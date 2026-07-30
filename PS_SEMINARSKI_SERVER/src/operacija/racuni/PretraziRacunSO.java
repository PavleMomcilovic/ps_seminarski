/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import domen.ApstraktniDomenskiObjekat;
import domen.Racun;
import domen.StavkaRacuna;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class PretraziRacunSO extends ApstraktnaGenerickaOperacija {

    private Racun rezultat;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Racun)) {
            throw new Exception("Sistem ne moze da nadje racune po zadatim kriterijumima: Prosledjen parametar nije tipa Racun");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Racun kriterijum = (Racun) param;
        String uslov = kriterijum.generisiKriterijumPretrazivanja();
        List<ApstraktniDomenskiObjekat> lista = broker.uzmiSve(kriterijum, uslov);
        if (lista.isEmpty()) {
            rezultat = null;
            return;
        }
        rezultat = (Racun) lista.get(0);
        ucitajStavke(rezultat);
        System.out.println("Sistem je nasao racun!");
    }
    
    private void ucitajStavke(Racun racun) throws Exception {
        StavkaRacuna kriterijum = new StavkaRacuna();
        kriterijum.setIdRacun(racun.getIdRacun());
        String uslov = kriterijum.generisiKriterijumPretrazivanja();
        List<ApstraktniDomenskiObjekat> stavke = broker.uzmiSve(kriterijum, uslov);
        
        List<StavkaRacuna> lista = new ArrayList<>();
        for (ApstraktniDomenskiObjekat stavka : stavke) {
            lista.add((StavkaRacuna) stavka);
        }
    }

    public Racun getRezultat() {
        return rezultat;
    }
}
