package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Gitara implements ApstraktniDomenskiObjekat {

    private Long idGitara;
    private String naziv;
    private float cena;
    private String vrsta;
    private String opis;

    public Gitara() {
    }

    public Gitara(Long idGitara, String naziv, float cena, String vrsta, String opis) {
        this.idGitara = idGitara;
        this.naziv = naziv;
        this.cena = cena;
        this.vrsta = vrsta;
        this.opis = opis;
    }

    public Long getIdGitara() {
        return idGitara;
    }

    public void setIdGitara(Long idGitara) {
        this.idGitara = idGitara;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Gitara{"
                + "idGitara=" + idGitara
                + ", naziv='" + naziv + '\''
                + ", cena=" + cena
                + ", vrsta='" + vrsta + '\''
                + ", opis='" + opis + '\''
                + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "gitara";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Long idGitara = rs.getLong("gitara.idGitara");
            String naziv = rs.getString("gitara.naziv");
            float cena = rs.getFloat("gitara.cena");
            String vrsta = rs.getString("gitara.vrsta");
            String opis = rs.getString("gitara.opis");

            Gitara gitara = new Gitara(idGitara, naziv, cena, vrsta, opis);
            lista.add(gitara);
        }

        System.out.println("KLASA GITARA: " + lista);
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,cena,vrsta,opis";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "'," + cena + ",'" + vrsta + "','" + opis + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "gitara.idGitara=" + idGitara;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        Gitara gitara = new Gitara();

        if (rs.next()) {
            Long idGitara = rs.getLong("gitara.idGitara");
            String naziv = rs.getString("gitara.naziv");
            float cena = rs.getFloat("gitara.cena");
            String vrsta = rs.getString("gitara.vrsta");
            String opis = rs.getString("gitara.opis");

            gitara = new Gitara(idGitara, naziv, cena, vrsta, opis);
        }

        System.out.println("KLASA GITARA: " + gitara);
        return gitara;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', cena=" + cena + ", vrsta='" + vrsta + "', opis='" + opis + "'";
    }

    @Override
    public String vratiKoloneZaCitanje() {
        return "idGitara,naziv,cena,vrsta,opis";
    }

    @Override
    public String generisiKriterijumPretrazivanja() {
        List<String> uslovi = new ArrayList<>();

        if (idGitara != null) {
            uslovi.add("gitara.idGitara=" + idGitara);
        }
        if (naziv != null && !naziv.isEmpty()) {
            uslovi.add("gitara.naziv='" + naziv + "'");
        }
        if (cena > 0) {
            uslovi.add("gitara.cena=" + cena);
        }
        if (vrsta != null && !vrsta.isEmpty()) {
            uslovi.add("gitara.vrsta='" + vrsta + "'");
        }
        if (opis != null && !opis.isEmpty()) {
            uslovi.add("gitara.opis='" + opis + "'");
        }

        return String.join(" AND ", uslovi);
    }
}
