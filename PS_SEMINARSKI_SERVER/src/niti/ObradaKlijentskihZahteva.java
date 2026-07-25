/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.Socket;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

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
                    case PRIJAVI_PRODAVCA:
                        
                        break;
                    default:
                        throw new AssertionError();
                }
            } catch (Exception e) {
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
