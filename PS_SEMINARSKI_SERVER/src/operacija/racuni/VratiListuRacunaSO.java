/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import domen.ApstraktniDomenskiObjekat;
import domen.Racun;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class VratiListuRacunaSO extends ApstraktnaGenerickaOperacija {
    private List<Racun> rezultat;

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
        rezultat = new ArrayList<>();
        for (ApstraktniDomenskiObjekat objekat : lista) {
            rezultat.add((Racun) objekat);
        }
        System.out.println("Sistem je nasao racune po zadatim kriterijumima!");
    }

    public List<Racun> getRezultat() {
        return rezultat;
    }
}
