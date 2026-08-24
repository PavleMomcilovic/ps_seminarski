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
    private Prodavac prodavac;
    private Kupac kupac;
    private List<StavkaRacuna> stavke = new ArrayList<>();

    public Racun() {
    }

    public Racun(Long idRacun, LocalDate datumIzdavanja, NacinPlacanja nacinPlacanja, float ukupanIznos, float popust, Prodavac prodavac, Kupac kupac) {
        this.idRacun = idRacun;
        this.datumIzdavanja = datumIzdavanja;
        this.nacinPlacanja = nacinPlacanja;
        this.ukupanIznos = ukupanIznos;
        this.popust = popust;
        this.prodavac = prodavac;
        this.kupac = kupac;
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

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
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
                + ", prodavac=" + prodavac
                + ", kupac=" + kupac
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

            Prodavac prodavac = new Prodavac();
            prodavac.setIdProdavac(idProdavac);

            Kupac kupac = new Kupac();
            kupac.setIdKupac(idKupac);

            Racun racun = new Racun(idRacun, datumIzdavanja, nacinPlacanja, ukupanIznos, popust, prodavac, kupac);
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
        return "'" + datumIzdavanja + "','" + nacinPlacanja + "'," + ukupanIznos + "," + popust + "," + prodavac.getIdProdavac() + "," + kupac.getIdKupac();
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

            Prodavac prodavac = new Prodavac();
            prodavac.setIdProdavac(idProdavac);

            Kupac kupac = new Kupac();
            kupac.setIdKupac(idKupac);

            racun = new Racun(idRacun, datumIzdavanja, nacinPlacanja, ukupanIznos, popust, prodavac, kupac);
        }

        System.out.println("KLASA RACUN: " + racun);
        return racun;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumIzdavanja='" + datumIzdavanja + "', nacinPlacanja='" + nacinPlacanja + "', ukupanIznos=" + ukupanIznos
                + ", popust=" + popust + ", idProdavac=" + prodavac.getIdProdavac() + ", idKupac=" + kupac.getIdKupac();
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
        if (prodavac != null && prodavac.getIdProdavac() != null) {
            uslovi.add("racun.idProdavac=" + prodavac.getIdProdavac());
        }
        if (kupac != null && kupac.getIdKupac() != null) {
            uslovi.add("racun.idKupac=" + kupac.getIdKupac());
        }

        return String.join(" AND ", uslovi);
    }
}
