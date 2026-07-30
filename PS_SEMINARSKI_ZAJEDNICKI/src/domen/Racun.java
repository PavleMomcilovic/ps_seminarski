package domen;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Racun implements ApstraktniDomenskiObjekat {

    private Long idRacun;
    private LocalDate datumIzdavanja;
    private NacinPlacanja nacinPlacanja;
    private float ukupanIznos;
    private float popust;
    private Long idProdavac;
    private Long idKupac;
    private List<StavkaRacuna> stavke = new ArrayList<>();

    public Racun() {
    }

    public Racun(Long idRacun, LocalDate datumIzdavanja, NacinPlacanja nacinPlacanja, float ukupanIznos, float popust, Long idProdavac, Long idKupac) {
        this.idRacun = idRacun;
        this.datumIzdavanja = datumIzdavanja;
        this.nacinPlacanja = nacinPlacanja;
        this.ukupanIznos = ukupanIznos;
        this.popust = popust;
        this.idProdavac = idProdavac;
        this.idKupac = idKupac;
    }

    public Long getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(Long idRacun) {
        this.idRacun = idRacun;
    }

    public LocalDate getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDate datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public NacinPlacanja getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(NacinPlacanja nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public float getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(float ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public float getPopust() {
        return popust;
    }

    public void setPopust(float popust) {
        this.popust = popust;
    }

    public Long getIdProdavac() {
        return idProdavac;
    }

    public void setIdProdavac(Long idProdavac) {
        this.idProdavac = idProdavac;
    }

    public Long getIdKupac() {
        return idKupac;
    }

    public void setIdKupac(Long idKupac) {
        this.idKupac = idKupac;
    }

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRacuna> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Racun{"
                + "idRacun=" + idRacun
                + ", datumIzdavanja=" + datumIzdavanja
                + ", nacinPlacanja='" + nacinPlacanja + '\''
                + ", ukupanIznos=" + ukupanIznos
                + ", popust=" + popust
                + ", idProdavac=" + idProdavac
                + ", idKupac=" + idKupac
                + ", stavke=" + stavke
                + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "racun";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Long idRacun = rs.getLong("racun.idRacun");
            LocalDate datumIzdavanja = rs.getObject("racun.datumIzdavanja", LocalDate.class);
            NacinPlacanja nacinPlacanja = NacinPlacanja.valueOf(rs.getString("racun.nacinPlacanja"));
            float ukupanIznos = rs.getFloat("racun.ukupanIznos");
            float popust = rs.getFloat("racun.popust");
            Long idProdavac = rs.getLong("racun.idProdavac");
            Long idKupac = rs.getLong("racun.idKupac");

            Racun racun = new Racun(idRacun, datumIzdavanja, nacinPlacanja, ukupanIznos, popust, idProdavac, idKupac);
            lista.add(racun);
        }

        System.out.println("KLASA RACUN: " + lista);
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumIzdavanja,nacinPlacanja,ukupanIznos,popust,idProdavac,idKupac";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + datumIzdavanja + "','" + nacinPlacanja + "'," + ukupanIznos + "," + popust + "," + idProdavac + "," + idKupac;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "racun.idRacun=" + idRacun;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Racun racun = new Racun();

        if (rs.next()) {
            Long idRacun = rs.getLong("racun.idRacun");
            LocalDate datumIzdavanja = rs.getObject("racun.datumIzdavanja", LocalDate.class);
            NacinPlacanja nacinPlacanja = NacinPlacanja.valueOf(rs.getString("racun.nacinPlacanja"));
            float ukupanIznos = rs.getFloat("racun.ukupanIznos");
            float popust = rs.getFloat("racun.popust");
            Long idProdavac = rs.getLong("racun.idProdavac");
            Long idKupac = rs.getLong("racun.idKupac");

            racun = new Racun(idRacun, datumIzdavanja, nacinPlacanja, ukupanIznos, popust, idProdavac, idKupac);
        }

        System.out.println("KLASA RACUN: " + racun);
        return racun;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumIzdavanja='" + datumIzdavanja + "', nacinPlacanja='" + nacinPlacanja + "', ukupanIznos=" + ukupanIznos
                + ", popust=" + popust + ", idProdavac=" + idProdavac + ", idKupac=" + idKupac;
    }

    @Override
    public String vratiKoloneZaCitanje() {
        return "idRacun,datumIzdavanja,nacinPlacanja,ukupanIznos,popust,idProdavac,idKupac";
    }

    @Override
    public String generisiKriterijumPretrazivanja() {
        List<String> uslovi = new ArrayList<>();

        if (idRacun != null) {
            uslovi.add("racun.idRacun=" + idRacun);
        }
        if (datumIzdavanja != null) {
            uslovi.add("racun.datumIzdavanja='" + datumIzdavanja + "'");
        }
        if (nacinPlacanja != null) {
            uslovi.add("racun.nacinPlacanja='" + nacinPlacanja + "'");
        }
        if (ukupanIznos > 0) {
            uslovi.add("racun.ukupanIznos=" + ukupanIznos);
        }
        if (popust > 0) {
            uslovi.add("racun.popust=" + popust);
        }
        if (idProdavac != null) {
            uslovi.add("racun.idProdavac=" + idProdavac);
        }
        if (idKupac != null) {
            uslovi.add("racun.idKupac=" + idKupac);
        }

        return String.join(" AND ", uslovi);
    }
}
