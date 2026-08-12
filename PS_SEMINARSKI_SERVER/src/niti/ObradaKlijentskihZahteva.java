/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Prodavac;
import java.io.IOException;
import java.net.Socket;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import kontroler.Kontroler;

/**
 *
 * @author pavle
 */
public class ObradaKlijentskihZahteva extends Thread {
    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    
    private boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();
                
                switch (zahtev.getOperacija()) {
                    case KREIRAJ_RACUN:
                        
                    case PRETRAZI_RACUN:
                    case PROMENI_RACUN:
                    case KREIRAJ_KUPCA:
                    case PRETRAZI_KUPCA:
                    case PROMENI_KUPCA:
                    case OBRISI_KUPCA:
                    case PRIJAVI_PRODAVCA:
                        Prodavac p = (Prodavac) zahtev.getParametar();
                        p = Kontroler.getInstanca().prijaviProdavca(p);
                        odgovor.setOdgovor(p);
                        break;
                    case KREIRAJ_PRODAVCA:
                    case PRETRAZI_PRODAVCA:
                    case PROMENI_PRODAVCA:
                    case OBRISI_PRODAVCA:
                    case KREIRAJ_GITARU:
                    case PRETRAZI_GITARU:
                    case PROMENI_GITARU:
                    case OBRISI_GITARU:
                    case KREIRAJ_MUZICKO_OBRAZOVANJE:
                    case PRETRAZI_MUZICKO_OBRAZOVANJE:
                    case PROMENI_MUZICKO_OBRAZOVANJE:
                    case OBRISI_MUZICKO_OBRAZOVANJE:
                    case UBACI_SMENU:
                    case PRETRAZI_SMENU:
                    case PROMENI_SMENU:
                    case OBRISI_SMENU:
                    default:
                        System.out.println("GRESKA, OPERACIJA NE POSTOJI!");
                }
                
                posiljalac.posalji(odgovor);
            } catch (Exception e) {
                System.out.println("KLASA KONTROLER: GRESKA PRILIKOM OBRADE ZAHTEVA");
                e.printStackTrace();
            }
        }
    }
    
    public void prekini() {
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Neuspesno prekidanje izvrsavanja klijenta!");
        }
        this.interrupt();
    }
}
