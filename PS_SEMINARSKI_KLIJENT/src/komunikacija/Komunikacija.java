/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Prodavac;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

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
    
    public synchronized Object posaljiZahtev(Operacija operacija, Object param) throws Exception {
        if (socket == null || soket.isClosed()) {
            throw new Exception("Nema aktivne konekcije sa serverom.");
        }

        Zahtev zahtev = new Zahtev(operacija, param);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getTipOdgovora() == TipOdgovora.GRESKA) {
            if (odgovor.getIzuzetak() != null) {
                throw odgovor.getIzuzetak();
            }
            throw new Exception("Nepoznata greska sa servera.");
        }
        if (odgovor.getTipOdgovora() == TipOdgovora.USPEH) {
            return odgovor.getRezultat();
        }
        Object rezultat = odgovor.getOdgovor();
        if (rezultat instanceof Exception) {
            throw (Exception) rezultat;
        }
        if (rezultat instanceof String && ((String) rezultat).startsWith("Greska:")) {
            throw new Exception((String) rezultat);
        }
        return rezultat;
    }

    public void zatvori() {
        try {
            if (primalac != null) primalac.zatvori();
            if (posiljalac != null) posiljalac.zatvori();
            if (soket != null && !soket.isClosed()) soket.close();
            System.out.println("Konekcija zatvorena.");
        } catch (Exception e) {
            System.out.println("Greska pri zatvaranju: " + e.getMessage());
        }
    }

    private Properties ucitajKonfiguraciju() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config/config.properties")) {
            properties.load(fis);
        } catch (Exception e) {
            properties.setProperty("HOST", "localhost");
            properties.setProperty("PORT", "9000");
        }
        return properties;
    }
}
