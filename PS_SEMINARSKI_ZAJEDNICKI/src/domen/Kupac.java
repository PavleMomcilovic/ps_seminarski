package domen;

import java.sql.ResultSet;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
