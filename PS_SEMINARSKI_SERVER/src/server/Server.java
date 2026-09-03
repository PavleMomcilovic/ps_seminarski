/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author pavle
 */
public class Server extends Thread {
    boolean kraj = false;
    ServerSocket serverSoket;
    List<ObradaKlijentskihZahteva> klijenti;

    public Server() {
        klijenti = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            serverSoket = new ServerSocket(pribaviPort());
            while (!kraj) {
                Socket s = serverSoket.accept();
                System.out.println("Klijent je povezan");

                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                klijenti.add(okz);
                okz.start();

            }
        } catch (IOException ex) {
            if (!kraj) {
                System.out.println("Server se gasi...");
            }
        }

    }

    public void zaustaviServer() {
        kraj = true;
        try {
            for (ObradaKlijentskihZahteva k : klijenti) {
                k.prekini();
            }
            if (serverSoket != null) {
                serverSoket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isKraj() {
        return kraj;
    }

    private int pribaviPort() {
        try {
            return Integer.parseInt(Konfiguracija.getInstanca().getProperty("port"));
        } catch (NumberFormatException ex) {
            System.out.println("Neispravan port u konfiguraciji, koristi se podrazumevani port 9000.");
            return 9000;
        }
    }
}
