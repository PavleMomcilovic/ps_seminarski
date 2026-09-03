/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.ApstraktniDomenskiObjekat;
import domen.Gitara;
import domen.Kupac;
import domen.MuzickoObrazovanje;
import domen.Prodavac;
import domen.Racun;
import domen.Smena;
import domen.StavkaRacuna;
import java.util.List;
import operacija.gitara.VratiListuGitaraSO;
import operacija.kupac.KreirajKupcaSO;
import operacija.kupac.ObrisiKupcaSO;
import operacija.kupac.PretraziKupcaSO;
import operacija.kupac.PromeniKupcaSO;
import operacija.kupac.VratiListuKupacaSO;
import operacija.muzickoobr.VratiListuMuzickoObrSO;
import operacija.prodavac.PrijaviProdavcaSO;
import operacija.prodavac.VratiListuProdavacaSO;
import operacija.racuni.KreirajRacunSO;
import operacija.racuni.PretraziRacunSO;
import operacija.racuni.PromeniRacunSO;
import operacija.racuni.VratiListuRacunaSO;
import operacija.smena.UbaciSmenuSO;
import operacija.stavkaracuna.ObrisiStavkuRacunaSO;
import operacija.stavkaracuna.PromeniStavkuRacunaSO;
import operacija.stavkaracuna.UbaciStavkuRacunaSO;
import operacija.stavkaracuna.VratiListuStavkiRacunaSO;

/**
 *
 * @author pavle
 */
public class Kontroler {

    private static Kontroler instanca;

    private Kontroler() {

    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            return new Kontroler();
        }
        return instanca;
    }

    public Prodavac prijaviProdavca(Prodavac p) throws Exception {
        PrijaviProdavcaSO operacija = new PrijaviProdavcaSO();
        operacija.izvrsi(p, null);
        System.out.println("KLASA KONTROLER: " + operacija.getProdavac());
        return operacija.getProdavac();
    }
    
    public void ubaci(ApstraktniDomenskiObjekat param) throws Exception {
        if (param instanceof Smena) {
            UbaciSmenuSO operacija = new UbaciSmenuSO();
            operacija.izvrsi(param, null);
        } else if (param instanceof StavkaRacuna) {
            UbaciStavkuRacunaSO operacija = new UbaciStavkuRacunaSO();
            operacija.izvrsi(param, null);
        } else {
            throw new Exception("Nepodrzan tip entiteta za ubacivanje: " + param.getClass().getSimpleName());
        }
    }

    public void kreiraj(ApstraktniDomenskiObjekat param) throws Exception {
        if (param instanceof Racun) {
            KreirajRacunSO operacija = new KreirajRacunSO();
            operacija.izvrsi(param, null);
        } else if (param instanceof Kupac) {
            KreirajKupcaSO operacija = new KreirajKupcaSO();
            operacija.izvrsi(param, null);
        } else {
            throw new Exception("Nepodrzan tip entiteta za kreiranje: " + param.getClass().getSimpleName());
        }
    }

    public ApstraktniDomenskiObjekat pretrazi(ApstraktniDomenskiObjekat param) throws Exception {
        if (param instanceof Racun) {
            PretraziRacunSO operacija = new PretraziRacunSO();
            operacija.izvrsi(param, null);
            return operacija.getRezultat();
        } else if (param instanceof Kupac) {
            PretraziKupcaSO operacija = new PretraziKupcaSO();
            operacija.izvrsi(param, null);
            return operacija.getRezultat();
        }
        throw new Exception("Nepodrzan tip entiteta za pretragu: " + param.getClass().getSimpleName());
    }
    
    public void promeni(ApstraktniDomenskiObjekat param) throws Exception {
        if (param instanceof Racun) {
            PromeniRacunSO operacija = new PromeniRacunSO();
            operacija.izvrsi(param, null);
        } else if (param instanceof Kupac) {
            PromeniKupcaSO operacija = new PromeniKupcaSO();
            operacija.izvrsi(param, null);
        } else if (param instanceof StavkaRacuna) {
            PromeniStavkuRacunaSO operacija = new PromeniStavkuRacunaSO();
            operacija.izvrsi(param, null);
        } else {
            throw new Exception("Nepodrzan tip entiteta za izmenu: " + param.getClass().getSimpleName());
        }
    }

    public void obrisi(ApstraktniDomenskiObjekat param) throws Exception {
        if (param instanceof Kupac) {
            ObrisiKupcaSO operacija = new ObrisiKupcaSO();
            operacija.izvrsi(param, null);
        } else if (param instanceof StavkaRacuna) {
            ObrisiStavkuRacunaSO operacija = new ObrisiStavkuRacunaSO();
            operacija.izvrsi(param, null);
        } else {
            throw new Exception("Nepodrzan tip entiteta za brisanje: " + param.getClass().getSimpleName());
        }
    }

    public List<? extends ApstraktniDomenskiObjekat> vratiListu(ApstraktniDomenskiObjekat param) throws Exception {
        if (param instanceof Racun) {
            VratiListuRacunaSO operacija = new VratiListuRacunaSO();
            operacija.izvrsi(param, null);
            return operacija.getRezultat();
        } else if (param instanceof Kupac) {
            VratiListuKupacaSO operacija = new VratiListuKupacaSO();
            operacija.izvrsi(param, null);
            return operacija.getRezultat();
        } else if (param instanceof Prodavac) {
            VratiListuProdavacaSO operacija = new VratiListuProdavacaSO();
            operacija.izvrsi(param, null);
            return operacija.getRezultat();
        } else if (param instanceof Gitara) {
            VratiListuGitaraSO operacija = new VratiListuGitaraSO();
            operacija.izvrsi(param, null);
            return operacija.getRezultat();
        } else if (param instanceof MuzickoObrazovanje) {
            VratiListuMuzickoObrSO operacija = new VratiListuMuzickoObrSO();
            operacija.izvrsi(param, null);
            return operacija.getRezultat();
        } else if (param instanceof StavkaRacuna) {
            VratiListuStavkiRacunaSO operacija = new VratiListuStavkiRacunaSO();
            operacija.izvrsi(param, null);
            return operacija.getRezultat();
        }
        throw new Exception("Nepodrzan tip entiteta za vracanje liste: " + param.getClass().getSimpleName());
    }
}
