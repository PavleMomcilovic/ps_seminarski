package domen;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProdavacSmena implements ApstraktniDomenskiObjekat {

    private Prodavac prodavac;
    private Smena smena;
    private LocalDate datum;

    public ProdavacSmena() {
    }

    public ProdavacSmena(Prodavac prodavac, Smena smena, LocalDate datum) {
        this.prodavac = prodavac;
        this.smena = smena;
        this.datum = datum;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public Smena getSmena() {
        return smena;
    }

    public void setSmena(Smena smena) {
        this.smena = smena;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "ProdavacSmena{"
                + "prodavac=" + prodavac
                + ", smena=" + smena
                + ", datum=" + datum
                + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "prodavacsmena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Long idProdavac = rs.getLong("prodavacsmena.idProdavac");
            Long idSmena = rs.getLong("prodavacsmena.idSmena");
            LocalDate datum = rs.getObject("prodavacsmena.datum", LocalDate.class);

            Prodavac prodavac = new Prodavac();
            prodavac.setIdProdavac(idProdavac);

            Smena smena = new Smena();
            smena.setIdSmena(idSmena);

            ProdavacSmena prodavacSmena = new ProdavacSmena(prodavac, smena, datum);
            lista.add(prodavacSmena);
        }

        System.out.println("KLASA PRODAVAC-SMENA: " + lista);
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idProdavac,idSmena,datum";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return prodavac.getIdProdavac() + "," + smena.getIdSmena() + ",'" + datum + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "prodavacsmena.idProdavac=" + prodavac.getIdProdavac()
                + " AND prodavacsmena.idSmena=" + smena.getIdSmena();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        ProdavacSmena prodavacSmena = new ProdavacSmena();

        while (rs.next()) {
            Long idProdavac = rs.getLong("prodavacsmena.idProdavac");
            Long idSmena = rs.getLong("prodavacsmena.idSmena");
            LocalDate datum = rs.getObject("prodavacsmena.datum", LocalDate.class);

            Prodavac prodavac = new Prodavac();
            prodavac.setIdProdavac(idProdavac);

            Smena smena = new Smena();
            smena.setIdSmena(idSmena);

            prodavacSmena = new ProdavacSmena(prodavac, smena, datum);
        }

        System.out.println("KLASA PRODAVAC-SMENA: " + prodavacSmena);
        return prodavacSmena;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datum='" + datum + "'";
    }

    @Override
    public String vratiKoloneZaCitanje() {
        return "idProdavac,idSmena,datum";
    }

    @Override
    public String generisiKriterijumPretrazivanja() {
        List<String> uslovi = new ArrayList<>();

        if (prodavac != null && prodavac.getIdProdavac() != null) {
            uslovi.add("prodavacsmena.idProdavac=" + prodavac.getIdProdavac());
        }
        if (smena != null && smena.getIdSmena() != null) {
            uslovi.add("prodavacsmena.idSmena=" + smena.getIdSmena());
        }
        if (datum != null) {
            uslovi.add("prodavacsmena.datum='" + datum + "'");
        }

        return String.join(" AND ", uslovi);
    }
}