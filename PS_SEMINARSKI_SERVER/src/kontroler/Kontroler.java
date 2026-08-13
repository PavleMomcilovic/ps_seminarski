/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.ApstraktniDomenskiObjekat;
import domen.Kupac;
import domen.Prodavac;
import domen.Racun;
import domen.Smena;
import java.util.List;
import operacija.kupac.KreirajKupcaSO;
import operacija.kupac.ObrisiKupcaSO;
import operacija.kupac.PretraziKupcaSO;
import operacija.kupac.PromeniKupcaSO;
import operacija.kupac.VratiListuKupacaSO;
import operacija.prodavac.PrijaviProdavcaSO;
import operacija.racuni.KreirajRacunSO;
import operacija.racuni.PretraziRacunSO;
import operacija.racuni.PromeniRacunSO;
import operacija.racuni.VratiListuRacunaSO;
import operacija.smena.UbaciSmenuSO;

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
        }
        throw new Exception("Operacija ubacivanja je podrzana samo za smenu.");
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
        } else if (param instanceof Kupac) {
            PretraziKupcaSO operacija = new PretraziKupcaSO();
            operacija.izvrsi(param, null);
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
        } else {
            throw new Exception("Nepodrzan tip entiteta za izmenu: " + param.getClass().getSimpleName());
        }
    }
    
    public void obrisi(ApstraktniDomenskiObjekat param) throws Exception {
        if (param instanceof Kupac) {
            ObrisiKupcaSO operacija = new ObrisiKupcaSO();
            operacija.izvrsi(param, null);
        } else {
            throw new Exception("Nepodrzan tip entiteta za brisanje: " + param.getClass().getSimpleName());
        }
    }

    public List<? extends ApstraktniDomenskiObjekat> vratiListu(ApstraktniDomenskiObjekat param) throws Exception {
        if (param instanceof Racun) {
            VratiListuRacunaSO operacija = new VratiListuRacunaSO();
            operacija.izvrsi(param, null);
        } else if (param instanceof Kupac) {
            VratiListuKupacaSO operacija = new VratiListuKupacaSO();
            operacija.izvrsi(param, null);
        }
        throw new Exception("Nepodrzan tip entiteta za vracanje liste: " + param.getClass().getSimpleName());
    }
}
