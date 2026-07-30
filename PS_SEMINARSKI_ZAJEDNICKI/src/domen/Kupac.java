package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Kupac implements ApstraktniDomenskiObjekat {

    private Long idKupac;
    private String imePrezime;
    private MuzickoObrazovanje muzickoObr;

    public Kupac() {
    }

    public Kupac(Long idKupac, String imePrezime, MuzickoObrazovanje muzickoObr) {
        this.idKupac = idKupac;
        this.imePrezime = imePrezime;
        this.muzickoObr = muzickoObr;
    }

    public Long getIdKupac() {
        return idKupac;
    }

    public void setIdKupac(Long idKupac) {
        this.idKupac = idKupac;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public MuzickoObrazovanje getMuzickoObr() {
        return muzickoObr;
    }

    public void setMuzickoObr(MuzickoObrazovanje muzickoObr) {
        this.muzickoObr = muzickoObr;
    }

    @Override
    public String toString() {
        return "Kupac{"
                + "idKupac=" + idKupac
                + ", imePrezime='" + imePrezime + '\''
                + ", idMuzickoObr=" + muzickoObr.getIdMuzickoObr()
                + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "kupac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Long idKupac = rs.getLong("kupac.idKupac");
            String imePrezime = rs.getString("kupac.imePrezime");
            Long idMuzickoObr = rs.getLong("kupac.idMuzickoObr");

            MuzickoObrazovanje muzickoObr = new MuzickoObrazovanje();
            muzickoObr.setIdMuzickoObr(idMuzickoObr);

            Kupac kupac = new Kupac(idKupac, imePrezime, muzickoObr);
            lista.add(kupac);
        }

        System.out.println("KLASA KUPAC: " + lista);
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime,idMuzickoObr";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + imePrezime + "', " + muzickoObr.getIdMuzickoObr();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kupac.idKupac=" + idKupac;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Kupac kupac = new Kupac();
        if (rs.next()) {
            Long idKupac = rs.getLong("kupac.idKupac");
            String imePrezime = rs.getString("kupac.imePrezime");
            Long idMuzickoObr = rs.getLong("kupac.idMuzickoObr");

            MuzickoObrazovanje muzickoObr = new MuzickoObrazovanje();
            muzickoObr.setIdMuzickoObr(idMuzickoObr);

            kupac = new Kupac(idKupac, imePrezime, muzickoObr);
        }

        System.out.println("KLASA KUPAC: " + kupac);
        return kupac;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imePrezime='" + imePrezime + "', idMuzickoObr=" + muzickoObr.getIdMuzickoObr();
    }

    @Override
    public String vratiKoloneZaCitanje() {
        return "idKupac,imePrezime,idMuzickoObr";
    }

    @Override
    public String generisiKriterijumPretrazivanja() {
        List<String> uslovi = new ArrayList<>();

        if (idKupac != null) {
            uslovi.add("kupac.idKupac=" + idKupac);
        }
        if (imePrezime != null && !imePrezime.isEmpty()) {
            uslovi.add("kupac.imePrezime='" + imePrezime + "'");
        }
        if (muzickoObr != null && muzickoObr.getIdMuzickoObr() != null) {
            uslovi.add("kupac.idMuzickoObr=" + muzickoObr.getIdMuzickoObr());
        }

        return String.join(" AND ", uslovi);
    }
}
