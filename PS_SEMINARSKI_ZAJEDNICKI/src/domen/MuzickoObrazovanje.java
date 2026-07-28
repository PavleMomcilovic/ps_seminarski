package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
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
        return "MuzickoObrazovanje{"
                + "idMuzickoObr=" + idMuzickoObr
                + ", stepen='" + stepen + '\''
                + ", popustPoStepenu=" + popustPoStepenu
                + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "muzickoobrazovanje";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Long idMuzickoObr = rs.getLong("muzickoobrazovanje.idMuzikoObr");
            Stepen stepen = Stepen.valueOf(rs.getString("muzickoobrazovanje.stepen"));
            float popustPoStepenu = rs.getFloat("muzickoobrazovanje.popustPoStepenu");

            MuzickoObrazovanje muzickoObr = new MuzickoObrazovanje(idMuzickoObr, stepen, popustPoStepenu);
            lista.add(muzickoObr);
        }

        System.out.println("KLASA MUZICKO OBRAZOVANJE: " + lista);
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "stepen,popustPoStepenu";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return stepen + "," + popustPoStepenu;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "muzickoobrazovanje.idMuzickoObr=" + idMuzickoObr;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        MuzickoObrazovanje muzickoObr = new MuzickoObrazovanje();

        if (rs.next()) {
            Long idMuzickoObr = rs.getLong("muzickoobrazovanje.idMuzikoObr");
            Stepen stepen = Stepen.valueOf(rs.getString("muzickoobrazovanje.stepen"));
            float popustPoStepenu = rs.getFloat("muzickoobrazovanje.popustPoStepenu");

            muzickoObr = new MuzickoObrazovanje(idMuzickoObr, stepen, popustPoStepenu);
        }

        System.out.println("KLASA MUZICKO OBRAZOVANJE: " + muzickoObr);
        return muzickoObr;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "stepen=" + stepen + ", popustPoStepenu=" + popustPoStepenu;
    }

    @Override
    public String vratiKoloneZaCitanje() {
        return "idMuzickoObr,stepen,popustPoStepenu";
    }
}
