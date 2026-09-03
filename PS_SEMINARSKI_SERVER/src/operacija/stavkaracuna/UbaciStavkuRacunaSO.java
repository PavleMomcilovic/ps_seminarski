/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavkaracuna;

import domen.ApstraktniDomenskiObjekat;
import domen.StavkaRacuna;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Pavle
 */
public class UbaciStavkuRacunaSO extends ApstraktnaGenerickaOperacija {

    private StavkaRacuna rezultat;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof StavkaRacuna))
            throw new Exception("Sistem ne moze da ubaci stavku racuna: Prosledjeni objekat nije tipa StavkaRacuna");

        StavkaRacuna stavka = (StavkaRacuna) param;
        if (stavka.getIdRacun() == null || stavka.getIdRacun() <= 0)
            throw new Exception("Sistem ne moze da ubaci stavku racuna: Stavka mora imati racun");
        if (stavka.getGitara() == null || stavka.getGitara().getIdGitara() == null || stavka.getGitara().getIdGitara() <= 0)
            throw new Exception("Sistem ne moze da ubaci stavku racuna: Stavka mora imati izabranu gitaru");
        if (stavka.getCenaStavke() <= 0)
            throw new Exception("Sistem ne moze da ubaci stavku racuna: Stavka mora imati cenu stavke");
        if (stavka.getKolicinaStavke() <= 0)
            throw new Exception("Sistem ne moze da ubaci stavku racuna: Stavka mora imati kolicinu stavke");
        if (stavka.getIznosStavke() <= 0)
            throw new Exception("Sistem ne moze da ubaci stavku racuna: Stavka mora imati iznos stavke");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        StavkaRacuna stavka = (StavkaRacuna) param;
        stavka.setRb(sledeciRedniBroj(stavka.getIdRacun()));
        broker.dodaj(stavka);
        rezultat = stavka;
        System.out.println("Sistem je ubacio stavku racuna!");
    }

    private Long sledeciRedniBroj(Long idRacun) throws Exception {
        StavkaRacuna kriterijum = new StavkaRacuna();
        kriterijum.setIdRacun(idRacun);
        String uslov = " WHERE " + kriterijum.generisiKriterijumPretrazivanja();
        List<ApstraktniDomenskiObjekat> stavke = broker.uzmiSve(kriterijum, uslov);

        long maksimalanRb = 0;
        for (ApstraktniDomenskiObjekat objekat : stavke) {
            Long rb = ((StavkaRacuna) objekat).getRb();
            if (rb != null && rb > maksimalanRb) {
                maksimalanRb = rb;
            }
        }
        return maksimalanRb + 1;
    }

    public StavkaRacuna getRezultat() {
        return rezultat;
    }
}
