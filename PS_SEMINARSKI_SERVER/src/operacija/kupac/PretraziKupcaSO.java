/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupac;

import domen.ApstraktniDomenskiObjekat;
import domen.Kupac;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class PretraziKupcaSO extends ApstraktnaGenerickaOperacija {
    private Kupac rezultat;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Kupac))
            throw new Exception("Sistem ne moze da nadje kupca po zadatim kriterijumima: Prosledjeni objekat nije tipa Kupac");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Kupac kriterijum = (Kupac) param;
        String uslov = kriterijum.generisiKriterijumPretrazivanja();
        if (!uslov.isEmpty()) {
            uslov = " WHERE " + uslov;
        }
        List<ApstraktniDomenskiObjekat> lista = broker.uzmiSve(kriterijum, uslov);
        if (lista.isEmpty()) {
            rezultat = null;
            System.out.println("Sistem ne moze da nadje kupca");
        }
        else {
            rezultat = (Kupac) lista.get(0);
            System.out.println("Sistem je nasao kupca");
        }
    }

    public Kupac getRezultat() {
        return rezultat;
    }
}
