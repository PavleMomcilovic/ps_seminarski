/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Prodavac;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author pavle
 */
public class Komunikacija {
    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instanca;

    private Komunikacija() {
    }

    public static Komunikacija getInstanca() {
        if (instanca == null) {
            instanca = new Komunikacija();
        }
        return instanca;
    }

    public void konekcija() {
        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException ex) {
            System.out.println("SERVER NIJE POVEZAN");
        }
    }
    
    public Prodavac login(String username, String password) {
        Prodavac p = new Prodavac();
        p.setUsername(username);
        p.setPassword(password);
        
        Zahtev zahtev = new Zahtev(Operacija.PRIJAVI_PRODAVCA, p);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        p = (Prodavac) odgovor.getOdgovor();
        return p;
    }
}
