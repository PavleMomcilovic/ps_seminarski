/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import domen.Racun;
import domen.StavkaRacuna;
import java.util.List;
import java.util.Objects;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class PromeniRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Sistem ne moze da promeni racun: Prosledjeni objekat nije tipa Racun");
        }

        Racun racun = (Racun) param;
        if (racun.getDatumIzdavanja() == null) {
            throw new Exception("Sistem ne moze da promeni racun: Racun mora da ima datum izdavanja");
        }
        if (racun.getKupac() == null || racun.getKupac().getIdKupac() == null || racun.getKupac().getIdKupac() <= 0) {
            throw new Exception("Sistem ne moze da promeni racun: Racun mora da ima kupca");
        }
        if (racun.getProdavac() == null || racun.getProdavac().getIdProdavac() == null || racun.getProdavac().getIdProdavac() <= 0) {
            throw new Exception("Sistem ne moze da promeni racun: Racun mora da ima prodavca");
        }
        if (racun.getNacinPlacanja() == null) {
            throw new Exception("Sistem ne moze da promeni racun: Racun mora da ima datum izdavanja");
        }
        for (StavkaRacuna stavka : racun.getStavke()) {
            if (stavka.getCenaStavke() == 0) {
                throw new Exception("Sistem ne moze da promeni racun: StavkaRacuna mora imati cenu stavke");
            }
            if (stavka.getIznosStavke() == 0) {
                throw new Exception("Sistem ne moze da promeni racun: StavkaRacuna mora imati iznos stavke");
            }
            if (stavka.getKolicinaStavke() == 0) {
                throw new Exception("Sistem ne moze da promeni racun: StavkaRacuna mora imati kolicinu stavke");
            }
            if (stavka.getGitara() == null || stavka.getGitara().getIdGitara() <= 0) {
                throw new Exception("Sistem ne moze da promeni racun: StavkaRacuna mora imati izabranu gitaru");
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Racun r = (Racun) param;

        try {
            broker.izmeni(r);
            azurirajStavke(r);

            System.out.println("Sistem je zapamtio racun");
        } catch (Exception e) {
            System.out.println("Sistem ne moze da zapamti racun: " + e.getMessage());
            throw e;
        }
    }

    private void azurirajStavke(Racun r) throws Exception {
        String uslov = " WHERE stavkaracuna.idRacun=" + r.getIdRacun();
        List<StavkaRacuna> postojeceStavke = broker.uzmiSve(new StavkaRacuna(), uslov);
        List<StavkaRacuna> noveStavke = r.getStavke();

        for (StavkaRacuna postojeca : postojeceStavke) {
            if (pronadjiPoRb(noveStavke, postojeca.getRb()) == null) {
                broker.obrisi(postojeca);
            }
        }

        for (StavkaRacuna nova : noveStavke) {
            nova.setIdRacun(r.getIdRacun());
            StavkaRacuna postojeca = pronadjiPoRb(postojeceStavke, nova.getRb());
            if (postojeca == null) {
                broker.dodaj(nova);
            } else if (!isteVrednosti(postojeca, nova)) {
                broker.izmeni(nova);
            }
        }
    }

    private StavkaRacuna pronadjiPoRb(List<StavkaRacuna> stavke, Long rb) {
        for (StavkaRacuna stavka : stavke) {
            if (stavka.getRb() != null && stavka.getRb().equals(rb)) {
                return stavka;
            }
        }
        return null;
    }

    private boolean isteVrednosti(StavkaRacuna a, StavkaRacuna b) {
        Long idGitareA = a.getGitara() != null ? a.getGitara().getIdGitara() : null;
        Long idGitareB = b.getGitara() != null ? b.getGitara().getIdGitara() : null;
        return a.getCenaStavke() == b.getCenaStavke()
                && a.getKolicinaStavke() == b.getKolicinaStavke()
                && a.getIznosStavke() == b.getIznosStavke()
                && Objects.equals(idGitareA, idGitareB);
    }
}
