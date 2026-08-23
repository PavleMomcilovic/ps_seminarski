/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.muzickoobr;

import domen.ApstraktniDomenskiObjekat;
import domen.MuzickoObrazovanje;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class VratiListuMuzickoObrSO extends ApstraktnaGenerickaOperacija {
    private List<MuzickoObrazovanje> rezultat;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof MuzickoObrazovanje))
            throw new Exception("Sistem ne moze da nadje muzicko obrazovanje po zadatim kriterijumima: Prosledjeni objekat nije tipa MuzickoObrazovanje");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        MuzickoObrazovanje kriterijum = (MuzickoObrazovanje) param;
        String uslov = kriterijum.generisiKriterijumPretrazivanja();
        List<ApstraktniDomenskiObjekat> lista = broker.uzmiSve(kriterijum, uslov);
        if (lista.isEmpty()) {
            rezultat = null;
            return;
        }
        rezultat = new ArrayList<>();
        for (ApstraktniDomenskiObjekat objekat : lista) {
            rezultat.add((MuzickoObrazovanje) objekat);
        }
        System.out.println("Sistem je nasao muzicka obrazovanja po zadatim kriterijumima!");
    }

    public List<MuzickoObrazovanje> getRezultat() {
        return rezultat;
    }
}
