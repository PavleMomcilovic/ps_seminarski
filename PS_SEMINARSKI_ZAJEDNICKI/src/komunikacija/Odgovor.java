/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author pavle
 */
public class Odgovor implements Serializable {
    private TipOdgovora tipOdgovora;
    private Object odgovor;   
    private Exception izuzetak;
    
    public Odgovor() {
    }

    public Odgovor(Object odgovor) {
        this.odgovor = odgovor;
    }
    
    public static Odgovor greska(Exception e) {
        Odgovor o = new Odgovor();
        o.tipOdgovora = TipOdgovora.GRESKA;
        o.izuzetak = e;
        return o;
    }

    public TipOdgovora getTipOdgovora() {
        return tipOdgovora;
    }

    public void setTipOdgovora(TipOdgovora tipOdgovora) {
        this.tipOdgovora = tipOdgovora;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public Exception getIzuzetak() {
        return izuzetak;
    }

    public void setIzuzetak(Exception izuzetak) {
        this.izuzetak = izuzetak;
    }
}
