/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racuni;

import domen.Racun;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author pavle
 */
public class KreirajRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun))
            throw new Exception("Sistem ne moze da kreira racun: Prosledjeni objekat nije tipa Racun");
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Racun racun = (Racun) param;
        Long racunId = broker.uzmiGenerisaniKljuc(param);
        racun.setIdRacun(racunId);
    }
    
}
