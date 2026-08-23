/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Gitara;
import domen.Kupac;
import domen.Prodavac;
import domen.Racun;
import java.io.IOException;
import java.net.Socket;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.TipOdgovora;
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
                Odgovor odgovor;
                try {
                    odgovor = obradiZahtev(zahtev);
                } catch (Exception e) {
                    System.out.println("KLASA OBRADAKLIJENTSKIHZAHTEVA: GRESKA PRILIKOM OBRADE ZAHTEVA");
                    e.printStackTrace();
                    odgovor = Odgovor.greska(e);
                }
                posiljalac.posalji(odgovor);
            } catch (Exception e) {
                System.out.println("KLASA OBRADAKLIJENTSKIHZAHTEVA: GRESKA PRILIKOM KOMUNIKACIJE");
                e.printStackTrace();
            }
        }
    }

    private Odgovor obradiZahtev(Zahtev zahtev) throws Exception {
        Odgovor odgovor = new Odgovor();

        switch (zahtev.getOperacija()) {
            case KREIRAJ_RACUN: {
                Racun racun = (Racun) zahtev.getParametar();
                Kontroler.getInstanca().kreiraj(racun);
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(racun);
                break;
            }
            case PRETRAZI_PRODAVCA: {
                Prodavac kriterijum = (Prodavac) zahtev.getParametar();
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(Kontroler.getInstanca().vratiListu(kriterijum));
                break;
            }
            case PRETRAZI_KUPCA: {
                Kupac kriterijum = (Kupac) zahtev.getParametar();
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(Kontroler.getInstanca().vratiListu(kriterijum));
                break;
            }
            case PRETRAZI_GITARU: {
                Gitara kriterijum = (Gitara) zahtev.getParametar();
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(Kontroler.getInstanca().vratiListu(kriterijum));
                break;
            }
            case PRIJAVI_PRODAVCA: {
                Prodavac p = (Prodavac) zahtev.getParametar();
                p = Kontroler.getInstanca().prijaviProdavca(p);
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(p);
                break;
            }
            case PRETRAZI_RACUN:
            case PROMENI_RACUN:
            case KREIRAJ_KUPCA:
            case PROMENI_KUPCA:
            case OBRISI_KUPCA:
            case UBACI_SMENU:
            default:
                throw new Exception("Operacija " + zahtev.getOperacija() + " jos nije implementirana na serveru.");
        }

        return odgovor;
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
