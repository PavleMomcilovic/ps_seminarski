/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import domen.Prodavac;
import forme.GlavnaForma;
import forme.LoginForma;
import java.util.HashMap;
import java.util.Map;
import kontoleri.GlavnaFormaKontroler;
import kontoleri.PrijaviProdavcaKontroler;

/**
 *
 * @author pavle
 */
public class Koordinator {
    private static Koordinator instanca;
    private Prodavac ulogovani;
    private PrijaviProdavcaKontroler prijaviProdavcaKontroler;
    private GlavnaFormaKontroler glavnaFormaKontroler;
    
    private Map<String, Object> parametri;
    
    private Koordinator() {
        parametri = new HashMap<>();
    }
    
    public static Koordinator getInstanca() {
        if (instanca == null)
            instanca = new Koordinator();
        return instanca;
    }

    public Prodavac getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Prodavac ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }

    public Object vratiParam(String s) {
        return parametri.get(s);
    }
    
    public void otvoriLoginFormu() {
        prijaviProdavcaKontroler = new PrijaviProdavcaKontroler(new LoginForma());
        prijaviProdavcaKontroler.otvoriFormu();
    }
    
    public void otvoriGlavnuFormu() {
        glavnaFormaKontroler = new GlavnaFormaKontroler(new GlavnaForma());
        glavnaFormaKontroler.otvoriFormu();
    }
}
