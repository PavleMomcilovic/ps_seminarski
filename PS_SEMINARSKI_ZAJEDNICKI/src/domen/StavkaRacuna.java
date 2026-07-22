package domen;

import java.sql.ResultSet;
import java.util.List;

public class StavkaRacuna implements ApstraktniDomenskiObjekat {

    private Long idRacun;
    private Long rb;
    private float cenaStavke;
    private int kolicinaStavke;
    private float iznosStavke;
    private Long idGitara;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Long idRacun, Long rb, float cenaStavke, int kolicinaStavke, float iznosStavke, Long idGitara) {
        this.idRacun = idRacun;
        this.rb = rb;
        this.cenaStavke = cenaStavke;
        this.kolicinaStavke = kolicinaStavke;
        this.iznosStavke = iznosStavke;
        this.idGitara = idGitara;
    }

    public Long getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(Long idRacun) {
        this.idRacun = idRacun;
    }

    public Long getRb() {
        return rb;
    }

    public void setRb(Long rb) {
        this.rb = rb;
    }

    public float getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(float cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    public int getKolicinaStavke() {
        return kolicinaStavke;
    }

    public void setKolicinaStavke(int kolicinaStavke) {
        this.kolicinaStavke = kolicinaStavke;
    }

    public float getIznosStavke() {
        return iznosStavke;
    }

    public void setIznosStavke(float iznosStavke) {
        this.iznosStavke = iznosStavke;
    }

    public Long getIdGitara() {
        return idGitara;
    }

    public void setIdGitara(Long idGitara) {
        this.idGitara = idGitara;
    }

    

    @Override
    public String toString() {
        return "StavkaRacuna{" +
                "idRacun=" + idRacun +
                ", rb=" + rb +
                ", cenaStavke=" + cenaStavke +
                ", kolicinaStavke=" + kolicinaStavke +
                ", iznosStavke=" + iznosStavke +
                ", idGitara=" + idGitara +
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
