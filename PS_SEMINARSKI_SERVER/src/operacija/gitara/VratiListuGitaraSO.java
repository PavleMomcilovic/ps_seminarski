/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.gitara;

import domen.ApstraktniDomenskiObjekat;
import domen.Gitara;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class VratiListuGitaraSO extends ApstraktnaGenerickaOperacija {
    private List<Gitara> rezultat;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Gitara))
            throw new Exception("Sistem ne moze da nadje gitaru po zadatim kriterijumima: Prosledjeni objekat nije tipa Gitara");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Gitara kriterijum = (Gitara) param;
        String uslov = kriterijum.generisiKriterijumPretrazivanja();
        List<ApstraktniDomenskiObjekat> lista = broker.uzmiSve(kriterijum, uslov);
        if (lista.isEmpty()) {
            rezultat = null;
            return;
        }
        rezultat = new ArrayList<>();
        for (ApstraktniDomenskiObjekat objekat : lista) {
            rezultat.add((Gitara) objekat);
        }
        System.out.println("Sistem je nasao prodavce po zadatim kriterijumima!");
    }

    public List<Gitara> getRezultat() {
        return rezultat;
    }
}
