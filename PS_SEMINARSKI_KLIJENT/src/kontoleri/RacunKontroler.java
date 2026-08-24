/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoleri;

import domen.Kupac;
import domen.Prodavac;
import domen.Racun;
import forme.PrikaziRacunForma;
import forme.model.ModelTabeleRacun;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import komunikacija.Operacija;

/**
 *
 * @author pavle
 */
public class RacunKontroler {
    private final PrikaziRacunForma forma;
    private ModelTabeleRacun modelTabele;

    public RacunKontroler(PrikaziRacunForma forma) {
        this.forma = forma;
        ucitajPocetneVrednosti();
        addActionListeners();
    }

    private void ucitajPocetneVrednosti() {
        modelTabele = new ModelTabeleRacun(new ArrayList<>());
        forma.getTblRacun().setModel(modelTabele);

        ucitajRacune();
    }

    private void ucitajRacune() {
        modelTabele.setLista(pribaviRacune());
    }

    private void addActionListeners() {
        forma.getBtnNazad().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forma.dispose();
            }
        });
    }

    private List<Racun> pribaviRacune() {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.VRATI_LISTU_RACUN, new Racun());
            List<Racun> lista = new ArrayList<>();
            if (rezultat != null) {
                for (Object o : (List<?>) rezultat) {
                    lista.add((Racun) o);
                }
            }
            dodajKupceProdavce(lista);
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da vrati listu racuna: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
    
    private void dodajKupceProdavce(List<Racun> racuni) {
        List<Kupac> kupci = pribaviKupce();
        Map<Long, Kupac> poIdK = new HashMap<>();
        
        List<Prodavac> prodavci = pribaviProdavce();
        Map<Long, Prodavac> poIdP = new HashMap<>();
        
        for (Kupac k : kupci) {
            poIdK.put(k.getIdKupac(), k);
        }
        
        for (Prodavac p : prodavci) {
            poIdP.put(p.getIdProdavac(), p);
        }
        
        for (Racun r : racuni) {
            if (r.getKupac() != null) {
                Kupac kupac = poIdK.get(r.getKupac().getIdKupac());
                if (kupac != null)
                    r.setKupac(kupac);
            }
            
            if (r.getProdavac() != null) {
                Prodavac prodavac = poIdP.get(r.getProdavac().getIdProdavac());
                if (prodavac != null)
                    r.setProdavac(prodavac);
            }
        }
    }
    
    private List<Kupac> pribaviKupce() {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.VRATI_LISTU_KUPAC, new Kupac());
            List<Kupac> lista = new ArrayList<>();
            if (rezultat != null) {
                for (Object o : (List<?>) rezultat) {
                    lista.add((Kupac) o);
                }
            }
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da vrati listu kupaca: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
    
    private List<Prodavac> pribaviProdavce() {
        try {
            Object rezultat = Komunikacija.getInstanca().posaljiZahtev(Operacija.VRATI_LISTU_PRODAVAC, new Prodavac());
            List<Prodavac> lista = new ArrayList<>();
            if (rezultat != null) {
                for (Object o : (List<?>) rezultat) {
                    lista.add((Prodavac) o);
                }
            }
            return lista;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da vrati listu prodavaca: " + ex.getMessage(),
                    "GRESKA", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
}
