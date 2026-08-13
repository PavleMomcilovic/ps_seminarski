/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.smena;

import domen.Smena;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class UbaciSmenuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Smena))
            throw new Exception("Sistem ne moze da zapamti smenu: Prosledjeni objekat nije tipa Smena");
        
        Smena smena = (Smena) param;
        if (smena.getIdSmena() <= 0)
            throw new Exception("Sistem ne moze da zapamti smenu: Smena mora imati identifikator");
        if (smena.getPocetakSmene() <= 0)
            throw new Exception("Sistem ne moze da zapamti smenu: Smena mora imati pocetak");
        if (smena.getKrajSmene() <= 0)
            throw new Exception("Sistem ne moze da zapamti smenu: Smena mora imati kraj");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.dodaj((Smena) param);
    }
    
}
