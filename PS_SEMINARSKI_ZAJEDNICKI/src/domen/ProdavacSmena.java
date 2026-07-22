package domen;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

public class ProdavacSmena implements ApstraktniDomenskiObjekat {

    private Long idProdavac;
    private Long idSmena;
    private LocalDate datum;

    public ProdavacSmena() {
    }

    public ProdavacSmena(Long idProdavac, Long idSmena, LocalDate datum) {
        this.idProdavac = idProdavac;
        this.idSmena = idSmena;
        this.datum = datum;
    }

    public Long getIdProdavac() {
        return idProdavac;
    }

    public void setIdProdavac(Long idProdavac) {
        this.idProdavac = idProdavac;
    }

    public Long getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(Long idSmena) {
        this.idSmena = idSmena;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "ProdavacSmena{" +
                "idProdavac=" + idProdavac +
                ", idSmena=" + idSmena +
                ", datum=" + datum +
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
