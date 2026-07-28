package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Kupac implements ApstraktniDomenskiObjekat {

    private Long idKupac;
    private String imePrezime;
    private Long idMuzickoObr;

    public Kupac() {
    }

    public Kupac(Long idKupac, String imePrezime, Long idMuzickoObr) {
        this.idKupac = idKupac;
        this.imePrezime = imePrezime;
        this.idMuzickoObr = idMuzickoObr;
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

    public Long getIdMuzickoObr() {
        return idMuzickoObr;
    }

    public void setIdMuzickoObr(Long idMuzickoObr) {
        this.idMuzickoObr = idMuzickoObr;
    }

    @Override
    public String toString() {
        return "Kupac{" +
                "idKupac=" + idKupac +
                ", imePrezime='" + imePrezime + '\'' +
                ", idMuzickoObr=" + idMuzickoObr +
                '}';
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
            
            Kupac kupac = new Kupac(idKupac, imePrezime, idMuzickoObr);
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
        return imePrezime + ", " + idMuzickoObr;
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
            
            kupac = new Kupac(idKupac, imePrezime, idMuzickoObr);
        }
        
        System.out.println("KLASA KUPAC: " + kupac);
        return kupac;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imePrezime=" + imePrezime + ", idMuzickoObr=" + idMuzickoObr;
    }

    @Override
    public String vratiKoloneZaCitanje() {
        return "idKupac,imePrezime,idMuzickoObr";
    }
}
