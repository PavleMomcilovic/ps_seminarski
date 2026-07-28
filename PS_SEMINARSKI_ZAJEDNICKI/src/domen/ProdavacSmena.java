package domen;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
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
        return "prodavacsmena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        while (rs.next()) {
            Long idProdavac = rs.getLong("prodavacsmena.idProdavac");
            Long idSmena = rs.getLong("prodavacsmena.idSmena");
            LocalDate datum = rs.getObject("prodavacsmena.datum", LocalDate.class);
            
            ProdavacSmena prodavacSmena = new ProdavacSmena(idProdavac, idSmena, datum);
            lista.add(prodavacSmena);
        }
        
        System.out.println("KLASA PRODAVAC-SMENA: " + lista);
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return datum.toString();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "prodavacsmena.idProdavac=" + idProdavac + ", prodavacsmena.idSmena=" + idSmena;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        ProdavacSmena prodavacSmena = new ProdavacSmena();
        
        while (rs.next()) {
            Long idProdavac = rs.getLong("prodavacsmena.idProdavac");
            Long idSmena = rs.getLong("prodavacsmena.idSmena");
            LocalDate datum = rs.getObject("prodavacsmena.datum", LocalDate.class);
            
            prodavacSmena = new ProdavacSmena(idProdavac, idSmena, datum);
        }
        
        System.out.println("KLASA PRODAVAC-SMENA: " + prodavacSmena);
        return prodavacSmena;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datum=" + datum;
    }

    @Override
    public String vratiKoloneZaCitanje() {
        return "idProdavac,idSmena,datum";
    }
}
