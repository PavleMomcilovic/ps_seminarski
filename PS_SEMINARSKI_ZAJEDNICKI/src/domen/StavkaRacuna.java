package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
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
        return "StavkaRacuna{"
                + "idRacun=" + idRacun
                + ", rb=" + rb
                + ", cenaStavke=" + cenaStavke
                + ", kolicinaStavke=" + kolicinaStavke
                + ", iznosStavke=" + iznosStavke
                + ", idGitara=" + idGitara
                + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaracuna";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Long idRacun = rs.getLong("stavkaracuna.idRacun");
            Long rb = rs.getLong("stavkaracuna.rb");
            float cenaStavke = rs.getFloat("stavkaracuna.cenaStavke");
            int kolicinaStavke = rs.getInt("stavkaracuna.kolicinaStavke");
            float iznosStavke = rs.getFloat("stavkaracuna.iznosStavke");
            Long idGitara = rs.getLong("stavkaracuna.idGitara");

            StavkaRacuna stavkaRacuna = new StavkaRacuna(idRacun, rb, cenaStavke, kolicinaStavke, iznosStavke, idGitara);
            lista.add(stavkaRacuna);
        }

        System.out.println("KLASA STAVKA RACUNA: " + lista);
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "cenaStavke,kolicinaStavke,iznosStavke,idGitara";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return cenaStavke + "," + kolicinaStavke + "," + iznosStavke + "," + idGitara;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavkaracuna.idRacun=" + idRacun + ", stavkaracuna.rb=" + rb;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        StavkaRacuna stavkaRacuna = new StavkaRacuna();

        if (rs.next()) {
            Long idRacun = rs.getLong("stavkaracuna.idRacun");
            Long rb = rs.getLong("stavkaracuna.rb");
            float cenaStavke = rs.getFloat("stavkaracuna.cenaStavke");
            int kolicinaStavke = rs.getInt("stavkaracuna.kolicinaStavke");
            float iznosStavke = rs.getFloat("stavkaracuna.iznosStavke");
            Long idGitara = rs.getLong("stavkaracuna.idGitara");

            stavkaRacuna = new StavkaRacuna(idRacun, rb, cenaStavke, kolicinaStavke, iznosStavke, idGitara);
        }

        System.out.println("KLASA STAVKA RACUNA: " + stavkaRacuna);
        return stavkaRacuna;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "cenaStavke=" + cenaStavke + ", kolicinaStavke=" + kolicinaStavke + ", iznosStavke=" + iznosStavke + ", idGitara=" + idGitara;
    }

    @Override
    public String vratiKoloneZaCitanje() {
        return "idRacun,rb,cenaStavke,kolicinaStavke,iznosStavke,idGitara";
    }
}
