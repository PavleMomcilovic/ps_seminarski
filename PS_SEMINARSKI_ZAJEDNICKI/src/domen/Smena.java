package domen;

import java.sql.ResultSet;
import java.util.List;

public class Smena implements ApstraktniDomenskiObjekat {

    private Long idSmena;
    private int pocetakSmene;
    private int krajSmene;

    public Smena() {
    }

    public Smena(Long idSmena, int pocetakSmene, int krajSmene) {
        this.idSmena = idSmena;
        this.pocetakSmene = pocetakSmene;
        this.krajSmene = krajSmene;
    }

    public Long getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(Long idSmena) {
        this.idSmena = idSmena;
    }

    public int getPocetakSmene() {
        return pocetakSmene;
    }

    public void setPocetakSmene(int pocetakSmene) {
        this.pocetakSmene = pocetakSmene;
    }

    public int getKrajSmene() {
        return krajSmene;
    }

    public void setKrajSmene(int krajSmene) {
        this.krajSmene = krajSmene;
    }

    @Override
    public String toString() {
        return "Smena{" +
                "idSmena=" + idSmena +
                ", pocetakSmene=" + pocetakSmene +
                ", krajSmene=" + krajSmene +
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
