/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupac;

import domen.ApstraktniDomenskiObjekat;
import domen.Kupac;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class VratiListuKupacaSO extends ApstraktnaGenerickaOperacija {
    private List<Kupac> rezultat;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Kupac))
            throw new Exception("Sistem ne moze da nadje kupca po zadatim kriterijumima: Prosledjeni objekat nije tipa Kupac");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Kupac kriterijum = (Kupac) param;
        String uslov = kriterijum.generisiKriterijumPretrazivanja();
        List<ApstraktniDomenskiObjekat> lista = broker.uzmiSve(kriterijum, uslov);
        if (lista.isEmpty()) {
            rezultat = null;
            return;
        }
        rezultat = new ArrayList<>();
        for (ApstraktniDomenskiObjekat objekat : lista) {
            rezultat.add((Kupac) objekat);
        }
        System.out.println("Sistem je nasao kupce po zadatim kriterijumima!");
    }

    public List<Kupac> getRezultat() {
        return rezultat;
    }
}
