package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
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
        return "Smena{"
                + "idSmena=" + idSmena
                + ", pocetakSmene=" + pocetakSmene
                + ", krajSmene=" + krajSmene
                + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "smena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Long idSmena = rs.getLong("smena.idSmena");
            int pocetakSmene = rs.getInt("smena.pocetakSmene");
            int krajSmene = rs.getInt("smena.krajSmene");

            Smena smena = new Smena(idSmena, pocetakSmene, krajSmene);
            lista.add(smena);
        }

        System.out.println("KLASA SMENA: " + lista);
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "pocetakSmene,krajSmene";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return pocetakSmene + "," + krajSmene;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "smena.idSmena=" + idSmena;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Smena smena = new Smena();

        if (rs.next()) {
            Long idSmena = rs.getLong("smena.idSmena");
            int pocetakSmene = rs.getInt("smena.pocetakSmene");
            int krajSmene = rs.getInt("smena.krajSmene");

            smena = new Smena(idSmena, pocetakSmene, krajSmene);
        }

        System.out.println("KLASA SMENA: " + smena);
        return smena;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "pocetakSmene=" + pocetakSmene + ", krajSmene=" + krajSmene;
    }

    @Override
    public String vratiKoloneZaCitanje() {
        return "idSmena,pocetakSmene,krajSmene";
    }
}
