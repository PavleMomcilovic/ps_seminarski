/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.prodavac;

import domen.ApstraktniDomenskiObjekat;
import domen.Prodavac;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class VratiListuProdavacaSO extends ApstraktnaGenerickaOperacija {
    private List<Prodavac> rezultat;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Prodavac))
            throw new Exception("Sistem ne moze da nadje prodavca po zadatim kriterijumima: Prosledjeni objekat nije tipa Prodavac");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Prodavac kriterijum = (Prodavac) param;
        String uslov = kriterijum.generisiKriterijumPretrazivanja();
        List<ApstraktniDomenskiObjekat> lista = broker.uzmiSve(kriterijum, uslov);
        if (lista.isEmpty()) {
            rezultat = null;
            return;
        }
        rezultat = new ArrayList<>();
        for (ApstraktniDomenskiObjekat objekat : lista) {
            rezultat.add((Prodavac) objekat);
        }
        System.out.println("Sistem je nasao prodavce po zadatim kriterijumima!");
    }

    public List<Prodavac> getRezultat() {
        return rezultat;
    }
}
