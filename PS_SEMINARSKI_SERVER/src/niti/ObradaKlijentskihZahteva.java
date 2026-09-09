/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Gitara;
import domen.Kupac;
import domen.MuzickoObrazovanje;
import domen.Prodavac;
import domen.Racun;
import domen.Smena;
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
                if (zahtev == null) {
                    prekini();
                    break;
                }
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
                Racun r = (Racun) zahtev.getParametar();
                Kontroler.getInstanca().kreiraj(r);
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(r);
                break;
            }
            case VRATI_LISTU_PRODAVAC: {
                Prodavac p = (Prodavac) zahtev.getParametar();
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(Kontroler.getInstanca().vratiListu(p));
                break;
            }
            case VRATI_LISTU_KUPAC: {
                Kupac k = (Kupac) zahtev.getParametar();
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(Kontroler.getInstanca().vratiListu(k));
                break;
            }
            case VRATI_LISTU_GITARA: {
                Gitara g = (Gitara) zahtev.getParametar();
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(Kontroler.getInstanca().vratiListu(g));
                break;
            }
            case VRATI_LISTU_MUZICKO_OBRAZOVANJE: {
                MuzickoObrazovanje mo = (MuzickoObrazovanje) zahtev.getParametar();
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(Kontroler.getInstanca().vratiListu(mo));
                break;
            }
            case KREIRAJ_KUPCA: {
                Kupac k = new Kupac();
                Kontroler.getInstanca().kreiraj(k);
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(k);
                break;
            }
            case PRIJAVI_PRODAVCA: {
                Prodavac p = (Prodavac) zahtev.getParametar();
                p = Kontroler.getInstanca().prijaviProdavca(p);
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(p);
                break;
            }
            case VRATI_LISTU_RACUN: {
                Racun kriterijum = (Racun) zahtev.getParametar();
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(Kontroler.getInstanca().vratiListu(kriterijum));
                break;
            }
            case PRETRAZI_RACUN: {
                Racun kriterijum = (Racun) zahtev.getParametar();
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(Kontroler.getInstanca().pretrazi(kriterijum));
                break;
            }
            case PRETRAZI_KUPCA: {
                Kupac kriterijum = (Kupac) zahtev.getParametar();
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(Kontroler.getInstanca().pretrazi(kriterijum));
                break;
            }
            case OBRISI_KUPCA: {
                Kupac kupac = (Kupac) zahtev.getParametar();
                Kontroler.getInstanca().obrisi(kupac);
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                break;
            }
            case UBACI_SMENU: {
                Smena smena = (Smena) zahtev.getParametar();
                Kontroler.getInstanca().ubaci(smena);
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(smena);
                break;
            }
            case PROMENI_RACUN: {
                Racun r = (Racun) zahtev.getParametar();
                Kontroler.getInstanca().promeni(r);
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(r);
                break;
            }
            case OBRISI_RACUN: {
                Racun r = (Racun) zahtev.getParametar();
                Kontroler.getInstanca().obrisi(r);
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                break;
            }
            case PROMENI_KUPCA: {
                Kupac k = (Kupac) zahtev.getParametar();
                Kontroler.getInstanca().promeni(k);
                odgovor.setTipOdgovora(TipOdgovora.USPEH);
                odgovor.setOdgovor(k);
                break;
            }
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
