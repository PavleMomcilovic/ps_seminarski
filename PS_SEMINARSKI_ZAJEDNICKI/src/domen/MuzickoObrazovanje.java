package domen;

import java.sql.ResultSet;
import java.util.List;

public class MuzickoObrazovanje implements ApstraktniDomenskiObjekat {

    private Long idMuzickoObr;
    private Stepen stepen;
    private float popustPoStepenu;

    public MuzickoObrazovanje() {
    }

    public MuzickoObrazovanje(Long idMuzickoObr, Stepen stepen, float popustPoStepenu) {
        this.idMuzickoObr = idMuzickoObr;
        this.stepen = stepen;
        this.popustPoStepenu = popustPoStepenu;
    }

    public Long getIdMuzickoObr() {
        return idMuzickoObr;
    }

    public void setIdMuzickoObr(Long idMuzickoObr) {
        this.idMuzickoObr = idMuzickoObr;
    }

    public Stepen getStepen() {
        return stepen;
    }

    public void setStepen(Stepen stepen) {
        this.stepen = stepen;
    }

    public float getPopustPoStepenu() {
        return popustPoStepenu;
    }

    public void setPopustPoStepenu(float popustPoStepenu) {
        this.popustPoStepenu = popustPoStepenu;
    }

    @Override
    public String toString() {
        return "MuzickoObrazovanje{" +
                "idMuzickoObr=" + idMuzickoObr +
                ", stepen='" + stepen + '\'' +
                ", popustPoStepenu=" + popustPoStepenu +
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
