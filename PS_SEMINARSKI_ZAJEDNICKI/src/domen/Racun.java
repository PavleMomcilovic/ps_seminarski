package domen;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

public class Racun implements ApstraktniDomenskiObjekat {

    private Long idRacun;
    private LocalDate datumIzdavanja;
    private NacinPlacanja nacinPlacanja;
    private float ukupanIznos;
    private float popust;
    private Long idProdavac;
    private Long idKupac;

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

    @Override
    public String toString() {
        return "Racun{" +
                "idRacun=" + idRacun +
                ", datumIzdavanja=" + datumIzdavanja +
                ", nacinPlacanja='" + nacinPlacanja + '\'' +
                ", ukupanIznos=" + ukupanIznos +
                ", popust=" + popust +
                ", idProdavac=" + idProdavac +
                ", idKupac=" + idKupac +
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
