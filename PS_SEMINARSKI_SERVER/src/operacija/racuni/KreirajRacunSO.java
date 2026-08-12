/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import domen.Racun;
import domen.StavkaRacuna;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class KreirajRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Sistem ne moze da kreira racun: Prosledjeni objekat nije tipa Racun");
        }

        Racun racun = (Racun) param;
        if (racun.getDatumIzdavanja() == null) {
            throw new Exception("Sistem ne moze da kreira racun: Racun mora da ima datum izdavanja");
        }
        if (racun.getIdKupac() == null || racun.getIdKupac() <= 0) {
            throw new Exception("Sistem ne moze da kreira racun: Racun mora da ima kupca");
        }
        if (racun.getIdProdavac() == null || racun.getIdProdavac() <= 0) {
            throw new Exception("Sistem ne moze da kreira racun: Racun mora da ima prodavca");
        }
        if (racun.getNacinPlacanja() == null) {
            throw new Exception("Sistem ne moze da kreira racun: Racun mora da ima datum izdavanja");
        }
        for (StavkaRacuna stavka : racun.getStavke()) {
            if (stavka.getCenaStavke() == 0) {
                throw new Exception("Sistem ne moze da kreira racun: StavkaRacuna mora imati cenu stavke");
            }
            if (stavka.getIznosStavke() == 0) {
                throw new Exception("Sistem ne moze da kreira racun: StavkaRacuna mora imati iznos stavke");
            }
            if (stavka.getKolicinaStavke() == 0) {
                throw new Exception("Sistem ne moze da kreira racun: StavkaRacuna mora imati kolicinu stavke");
            }
            if (stavka.getGitara() == null || stavka.getGitara().getIdGitara() <= 0) {
                throw new Exception("Sistem ne moze da kreira racun: StavkaRacuna mora imati izabranu gitaru");
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Racun r = (Racun) param;
        Long idRacun = broker.uzmiGenerisaniKljuc(r);
        r.setIdRacun(idRacun);

        List<StavkaRacuna> stavke = r.getStavke();
        for (StavkaRacuna s : stavke) {
            s.setIdRacun(idRacun);
            broker.dodaj(s);
        }
    }
}
