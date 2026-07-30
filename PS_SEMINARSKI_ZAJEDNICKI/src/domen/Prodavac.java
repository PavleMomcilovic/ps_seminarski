package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Prodavac implements ApstraktniDomenskiObjekat {

    private Long idProdavac;
    private String imePrezime;
    private int plata;
    private String username;
    private String password;

    public Prodavac() {
    }

    public Prodavac(Long idProdavac, String imePrezime, int plata, String username, String password) {
        this.idProdavac = idProdavac;
        this.imePrezime = imePrezime;
        this.plata = plata;
        this.username = username;
        this.password = password;
    }

    public Long getIdProdavac() {
        return idProdavac;
    }

    public void setIdProdavac(Long idProdavac) {
        this.idProdavac = idProdavac;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public int getPlata() {
        return plata;
    }

    public void setPlata(int plata) {
        this.plata = plata;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Prodavac{"
                + "idProdavac=" + idProdavac
                + ", imePrezime='" + imePrezime + '\''
                + ", plata=" + plata
                + ", username='" + username + '\''
                + ", password='" + password + '\''
                + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "prodavac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Long idProdavac = rs.getLong("prodavac.idProdavac");
            String imePrezime = rs.getString("prodavac.imePrezime");
            int plata = rs.getInt("prodavac.plata");
            String username = rs.getString("prodavac.username");
            String password = rs.getString("prodavac.password");

            Prodavac prodavac = new Prodavac(idProdavac, imePrezime, plata, username, password);
            lista.add(prodavac);
        }

        System.out.println("KLASA PRODAVAC: " + lista);
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime,plata,username,password";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + imePrezime + "'," + plata + ",'" + username + "','" + password + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "prodavac.idProdavac=" + idProdavac;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Prodavac prodavac = new Prodavac();

        if (rs.next()) {
            Long idProdavac = rs.getLong("prodavac.idProdavac");
            String imePrezime = rs.getString("prodavac.imePrezime");
            int plata = rs.getInt("prodavac.plata");
            String username = rs.getString("prodavac.username");
            String password = rs.getString("prodavac.password");

            prodavac = new Prodavac(idProdavac, imePrezime, plata, username, password);
        }

        System.out.println("KLASA PRODAVAC: " + prodavac);
        return prodavac;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imePrezime='" + imePrezime + "', plata=" + plata + ", username='" + username + "', password='" + password + "'";
    }

    @Override
    public String vratiKoloneZaCitanje() {
        return "idProdavac,imePrezime,plata,username,password";
    }

    @Override
    public String generisiKriterijumPretrazivanja() {
        List<String> uslovi = new ArrayList<>();

        if (idProdavac != null) {
            uslovi.add("prodavac.idProdavac=" + idProdavac);
        }
        if (imePrezime != null && !imePrezime.isEmpty()) {
            uslovi.add("prodavac.imePrezime='" + imePrezime + "'");
        }
        if (plata > 0) {
            uslovi.add("prodavac.plata=" + plata);
        }
        if (username != null && !username.isEmpty()) {
            uslovi.add("prodavac.username='" + username + "'");
        }
        if (password != null && !password.isEmpty()) {
            uslovi.add("prodavac.password='" + password + "'");
        }

        return String.join(" AND ", uslovi);
    }
}
